package com.example.hackernews.data.source.remote

import com.example.hackernews.data.models.HackerNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/search_by_date")
    suspend fun getHackerNews(
        @Query("query") query: String = "mobile",
        @Query("page") numberPage: Int,
        @Query("tags") tags: String = "story"
    ) : Response<HackerNewsResponse>
}