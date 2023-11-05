package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.local.entity.BookReviewEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BookReviewDao {
    @Query("SELECT * FROM BookReviewEntity")
    fun getAllBookReviews(): Flow<List<BookReviewEntity>>

    @Query("SELECT * FROM BookReviewEntity WHERE id = :id")
    suspend fun getBookReviewById(id: Int): BookReviewEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookReview(bookReviewEntity: BookReviewEntity)

    @Update
    suspend fun updateBookReview(bookReviewEntity: BookReviewEntity)

    @Delete
    suspend fun deleteBookReview(bookReviewEntity: BookReviewEntity)
}