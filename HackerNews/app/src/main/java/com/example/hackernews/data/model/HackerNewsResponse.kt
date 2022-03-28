package com.example.hackernews.data.models

data class HackerNewsResponse(
    val exhaustiveNbHits: Boolean,
    val exhaustiveTypo: Boolean,
    val hits: MutableList<Hit>,
    val hitsPerPage: Int,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int,
    val params: String,
    val processingTimeMS: Int,
    val query: String
)