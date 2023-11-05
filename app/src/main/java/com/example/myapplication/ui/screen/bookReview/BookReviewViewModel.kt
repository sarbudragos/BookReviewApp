package com.example.myapplication.ui.screen.bookReview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.BookReview
import com.example.myapplication.domain.repository.BookReviewRepository
import com.example.myapplication.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookReviewViewModel @Inject constructor(
    private val repository: BookReviewRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(BookReviewState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getBookReviewById(id)?.let { bookReview ->
                    _state.update { screenState ->
                        screenState.copy(
                            id = bookReview.id,
                            name = bookReview.name,
                            author = bookReview.author,
                            publishingYear = bookReview.publishingYear,
                            reviewScore = bookReview.reviewScore,
                            comment = bookReview.comment

                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: BookReviewEvent) {
        when (event) {
            is BookReviewEvent.NameChange -> {

                _state.update {
                    it.copy(
                        name = event.value
                    )
                }
            }

            is BookReviewEvent.AuthorChange -> {
                _state.update {
                    it.copy(
                        author = event.value
                    )
                }
            }
            is BookReviewEvent.PublishingYearChange -> {
                _state.update {
                    it.copy(
                        publishingYear = event.value
                    )
                }
            }

            is BookReviewEvent.ScoreChange -> {
                _state.update {
                    it.copy(
                        reviewScore = event.value
                    )
                }
            }

            is BookReviewEvent.CommentChange -> {
                _state.update {
                    it.copy(
                        comment = event.value
                    )
                }
            }

            BookReviewEvent.NavigateBack -> sendEvent(UiEvent.NavigateBack)
            BookReviewEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val bookReview = BookReview(
                        id = state.id,
                        name = state.name,
                        author = state.author,
                        publishingYear = state.publishingYear,
                        reviewScore = state.reviewScore,
                        comment = state.comment
                    )
                    if (state.id == null) {
                        repository.insertBookReview(bookReview)
                    } else {
                        repository.updateBookReview(bookReview)
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }

            BookReviewEvent.DeleteBookReview -> {
                viewModelScope.launch {
                    val state = state.value
                    val bookReview = BookReview(
                        id = state.id,
                        name = state.name,
                        author = state.author,
                        publishingYear = state.publishingYear,
                        reviewScore = state.reviewScore,
                        comment = state.comment
                    )
                    repository.deleteBookReview(bookReview)
                }
                sendEvent(UiEvent.NavigateBack)
            }
        }
    }
}