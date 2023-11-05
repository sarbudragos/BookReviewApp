package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.BookReviewDao
import com.example.myapplication.data.mapper.asExternalModel
import com.example.myapplication.data.mapper.toEntity
import com.example.myapplication.domain.model.BookReview
import com.example.myapplication.domain.repository.BookReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookReviewRepositoryImplementation(
    private val dao: BookReviewDao
) : BookReviewRepository {
    override fun getAllBookReviews(): Flow<List<BookReview>> {
        return dao.getAllBookReviews().map {
            bookReviews -> bookReviews.map {
                it.asExternalModel()
            }
        }
    }

    override suspend fun getBookReviewById(id: Int): BookReview? {
        return dao.getBookReviewById(id)?.asExternalModel()
    }

    override suspend fun insertBookReview(bookReview: BookReview) {
        dao.insertBookReview(bookReview.toEntity())
    }

    override suspend fun updateBookReview(bookReview: BookReview) {
        dao.updateBookReview(bookReview.toEntity())
    }

    override suspend fun deleteBookReview(bookReview: BookReview) {
        dao.deleteBookReview(bookReview.toEntity())
    }
}