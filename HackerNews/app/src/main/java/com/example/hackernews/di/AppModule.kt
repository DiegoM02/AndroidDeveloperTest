package com.example.hackernews.di

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hackernews.Hilt_HitlApplication
import com.example.hackernews.HitlApplication
import com.example.hackernews.data.models.Hit
import com.example.hackernews.data.repositories.NewsRepositoryMain
import com.example.hackernews.data.repository.HackerNewsRoomRepository
import com.example.hackernews.data.room.HackerNewsDao
import com.example.hackernews.data.room.HackerNewsDataBase
import com.example.hackernews.data.source.remote.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.hackernews.data.source.remote.RetrofitServices
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitServices.geInstance()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRepository(api: ApiService): NewsRepositoryMain {
        return NewsRepositoryMain(api)
    }

    @Provides
    @Singleton
    fun providesDataBaseRoom(@ApplicationContext context: Context): HackerNewsDataBase {
        return Room.databaseBuilder(
            context,
            HackerNewsDataBase::class.java, "hacker_news"
        ).
        build()
    }

    @Provides
    fun  providesHackerNewDao(db: HackerNewsDataBase): HackerNewsDao = db.hackerNewsDao()

    @Provides
    fun providesRepositoryRoom(hackerNewsDao: HackerNewsDao, hackerNewsApi: ApiService): HackerNewsRoomRepository = HackerNewsRoomRepository(hackerNewsDao, hackerNewsApi)

    @Provides
    @Singleton
    fun providesHitApplication() = HitlApplication()


}