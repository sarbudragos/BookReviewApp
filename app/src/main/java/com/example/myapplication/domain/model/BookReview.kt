package com.example.myapplication.domain.model

data class BookReview (
    val id: Int? = null,
    val name: String = "",
    val author: String = "",
    val publishingYear: Int? = 0,
    val reviewScore: Int? = 0,
    val comment: String = ""
)