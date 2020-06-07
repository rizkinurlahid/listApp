package com.example.listapp.model

data class ApiModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)