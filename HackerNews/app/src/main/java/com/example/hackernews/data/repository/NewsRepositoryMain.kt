package com.example.hackernews.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hackernews.data.models.HackerNewsResponse
import com.example.hackernews.data.models.Hit
import com.example.hackernews.data.source.remote.ApiService
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryMain @Inject constructor(
    private val api: ApiService
    ): PagingSource<Int, Hit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        return try {

            val nextPageNumber = params.key ?: 0
            val response = api.getHackerNews(numberPage = nextPageNumber)
             LoadResult.Page(
                data = response.body()!!.hits,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null, // Only paging forward.
                nextKey = if (nextPageNumber < response.body()!!.nbPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }



    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}