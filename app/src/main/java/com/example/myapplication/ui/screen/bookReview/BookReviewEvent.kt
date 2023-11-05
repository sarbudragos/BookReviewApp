package com.example.myapplication.ui.screen.bookReview

sealed interface BookReviewEvent{
    data class NameChange(val value: String): BookReviewEvent
    data class AuthorChange(val value: String): BookReviewEvent
    data class ScoreChange(val value: Int?): BookReviewEvent
    data class PublishingYearChange(val value: Int?): BookReviewEvent
    data class CommentChange(val value: String): BookReviewEvent
    object Save: BookReviewEvent
    object NavigateBack: BookReviewEvent
    object DeleteBookReview : BookReviewEvent
}