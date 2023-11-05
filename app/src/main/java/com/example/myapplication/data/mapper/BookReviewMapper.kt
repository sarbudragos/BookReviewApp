package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.BookReviewEntity
import com.example.myapplication.domain.model.BookReview

fun BookReviewEntity.asExternalModel(): BookReview = BookReview(
    id, name, author, publishingYear, reviewScore, comment
)

fun BookReview.toEntity(): BookReviewEntity = BookReviewEntity(
    id, name, author, publishingYear, reviewScore, comment
)