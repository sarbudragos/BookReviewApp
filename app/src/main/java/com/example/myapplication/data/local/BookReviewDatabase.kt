package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.BookReviewDao
import com.example.myapplication.data.local.entity.BookReviewEntity

@Database(
    version = 1,
    entities = [BookReviewEntity::class],
    exportSchema = false
    )
abstract class BookReviewDatabase : RoomDatabase() {
    abstract val dao: BookReviewDao

    companion object{
        const val name = "book_review_db"
    }
}