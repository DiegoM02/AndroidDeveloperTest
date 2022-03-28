package com.example.hackernews.domain.uses_cases

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.hackernews.data.HackerNewsMediator
import com.example.hackernews.data.repositories.NewsRepositoryMain
import com.example.hackernews.data.repository.HackerNewsRoomRepository
import javax.inject.Inject

class GetHackerNewsRoomUseCase @Inject constructor(
   private val repository: HackerNewsRoomRepository
) {
    fun invoke() = repository.getAllHackerNewsRoom()
}