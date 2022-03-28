package com.example.hackernews.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.hackernews.data.models.Hit
import com.example.hackernews.data.room.HackerNewsDao
import com.example.hackernews.data.room.HitRemoteKey
import com.example.hackernews.data.source.remote.ApiService
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class HackerNewsMediator(

    private val newsDao: HackerNewsDao,
    private val newsInterface: ApiService,
    private val initialPage: Int = 0

) : RemoteMediator<Int, Hit>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hit>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey =
                        getLastRemoteKey(state)
                            ?: throw InvalidObjectException("An Occured a error")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = false)

                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = false)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeys(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }
            val response = newsInterface.getHackerNews(
                numberPage = page,
            )
            val endOfPagination = response.body()?.nbHits!! < state.config.pageSize

            if (response.isSuccessful) {
                response.body()?.let {
                    if (loadType == LoadType.REFRESH) {
                        newsDao.deleteAllArticles()
                        newsDao.deleteAllRemoteKeys()
                    }
                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1
                    val list = response.body()?.hits?.map {
                        HitRemoteKey(it.objectID!!, prev, next)
                    }
                    if (list != null) {
                        newsDao.insertAllRemoteKeys(list)
                    }
                    newsDao.insertAll(it.hits)
                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = false)
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosestRemoteKeys(state: PagingState<Int, Hit>): HitRemoteKey? {

        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                newsDao.getAllRemoteKey(it.objectID!!)
            }
        }

    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, Hit>): HitRemoteKey? {
        return state.lastItemOrNull()?.let {
            newsDao.getAllRemoteKey(it.objectID!!)
        }
    }
}