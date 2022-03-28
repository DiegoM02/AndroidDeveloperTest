package com.example.hackernews.ui.list_news

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hackernews.HitlApplication
import com.example.hackernews.data.models.Hit
import com.example.hackernews.domain.uses_cases.GetHackerNewsRoomUseCase
import com.example.hackernews.domain.uses_cases.GetHackerNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListHackerNewsViewModel @Inject constructor(
    private val getHackerNewsRoomUseCase: GetHackerNewsRoomUseCase,
) : ViewModel() {
    val hackerNews = getHackerNewsRoomUseCase.invoke().cachedIn(viewModelScope)

    fun getAllNews(): Flow<PagingData<Hit>> {
        val hackerNews = getHackerNewsRoomUseCase.invoke().cachedIn(viewModelScope)
        return hackerNews
    }



}