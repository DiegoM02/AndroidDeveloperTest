package com.example.hackernews.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
@Entity
data class Hit(
    val _highlightResult: HighlightResult?,
    val _tags: List<String>?,
    val author: String?,
    val comment_text: String?,
    val created_at: String?,
    val created_at_i: Int?,
    val num_comments: Int?,
    val objectID: String?,
    val parent_id: Int?,
    val points: Int?,
    @PrimaryKey val story_id: Int?,
    val story_text: String?,
    val story_title: String?,
    val story_url: String?,
    val title: String?,
    val url: String?
) : Parcelable