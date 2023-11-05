package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookReviewEntity (
    @PrimaryKey val id: Int?,
    val name: String,
    val author: String,
    val publishingYear: Int?,
    val reviewScore: Int?,
    val comment: String
)