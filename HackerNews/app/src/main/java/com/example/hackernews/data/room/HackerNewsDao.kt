package com.example.hackernews.data.room

import androidx.paging.PagingSource
import androidx.room.*
import com.example.hackernews.data.models.Hit

@Dao
interface HackerNewsDao {

    @Query("SELECT * FROM Hit")
    fun getAll(): PagingSource<Int, Hit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(hackerNew: List<Hit>)

    @Update
    suspend fun update(hackerNew: Hit)

    @Query("DELETE FROM Hit")
    suspend fun deleteAllArticles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleSingle(article: Hit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(list: List<HitRemoteKey>)

    @Query("SELECT * FROM HitRemoteKey WHERE id = :id")
    suspend fun getAllRemoteKey(id: String): HitRemoteKey?

    @Query("DELETE FROM HitRemoteKey")
    suspend fun deleteAllRemoteKeys()
}