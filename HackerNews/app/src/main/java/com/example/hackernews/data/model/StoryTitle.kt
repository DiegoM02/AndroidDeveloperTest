package com.example.hackernews.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryTitle(
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
) : Parcelable