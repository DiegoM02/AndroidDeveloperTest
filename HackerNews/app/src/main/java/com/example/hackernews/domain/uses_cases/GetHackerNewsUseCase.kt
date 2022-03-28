package com.example.hackernews.domain.uses_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.hackernews.data.repositories.NewsRepositoryMain
import javax.inject.Inject

class GetHackerNewsUseCase @Inject constructor(
        private val repository: NewsRepositoryMain
    ) {
    fun invoke() = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        repository
    }).flow
}
