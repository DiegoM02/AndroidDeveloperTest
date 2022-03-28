package com.example.hackernews.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HighlightResult(
    val author: Author,
    val comment_text: CommentText,
    val story_text: StoryText,
    val story_title: StoryTitle,
    val story_url: StoryUrl,
    val title: Title,
    val url: Url
) : Parcelable