package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.BookReview
import kotlinx.coroutines.flow.Flow

interface BookReviewRepository {
    fun getAllBookReviews(): Flow<List<BookReview>>

    suspend fun getBookReviewById(id: Int): BookReview?

    suspend fun insertBookReview(bookReview: BookReview)

    suspend fun updateBookReview(bookReview: BookReview)

    suspend fun deleteBookReview(bookReview: BookReview)
}