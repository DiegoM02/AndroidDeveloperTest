package com.example.hackernews.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryUrl(
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
) : Parcelable