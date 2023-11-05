package com.example.myapplication.ui.screen.bookReview

data class BookReviewState(
    val id: Int? = null,
    val name: String = "",
    val author: String = "",
    val publishingYear: Int? = 0,
    val reviewScore: Int? = 0,
    val comment: String = ""
)
