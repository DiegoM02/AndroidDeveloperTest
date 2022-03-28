package com.example.hackernews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.hackernews.data.HackerNewsMediator
import com.example.hackernews.data.room.HackerNewsDao
import com.example.hackernews.data.source.remote.ApiService
import javax.inject.Inject

class HackerNewsRoomRepository @Inject constructor(
    private val hackerNewsDao: HackerNewsDao,
    private val hackerNewsApi: ApiService
) {
    @OptIn(ExperimentalPagingApi::class) fun getAllHackerNewsRoom() =  Pager(
        PagingConfig(pageSize = 10000),
        remoteMediator = HackerNewsMediator(hackerNewsDao,hackerNewsApi,0)
    ) {
       hackerNewsDao.getAll()
    }.flow

}