package com.example.hackernews.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hackernews.data.models.HighlightResult
import com.example.hackernews.data.models.Hit

@Entity
data class HitRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prev: Int?,
    val next: Int?
)