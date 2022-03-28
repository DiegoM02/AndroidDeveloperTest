package com.example.hackernews.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hackernews.data.models.Hit


@Database(
    entities = [Hit::class, HitRemoteKey::class],
    version = 1

)
@TypeConverters(RoomTypeConvertor::class)
abstract class HackerNewsDataBase  : RoomDatabase(){

    abstract  fun hackerNewsDao(): HackerNewsDao
}