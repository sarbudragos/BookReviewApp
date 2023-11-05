package com.example.myapplication.ui.screen.bookReview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookReviewScreen(
    state: BookReviewState,
    onEvent: (BookReviewEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(BookReviewEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(BookReviewEvent.DeleteBookReview)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = {
                    onEvent(BookReviewEvent.NameChange(it))
                },
                placeholder = {
                    Text(text = "Book Name")
                }
            )
            OutlinedTextField(
                value = state.author,
                onValueChange = {
                    onEvent(BookReviewEvent.AuthorChange(it))
                },
                placeholder = {
                    Text(text = "Author")
                }
            )

            OutlinedTextField(
                value = state.publishingYear.toString(),
                onValueChange = {

                    onEvent(BookReviewEvent.PublishingYearChange(it.toIntOrNull()?:0))
                },
                placeholder = {
                    Text(text = "Publishing Year")
                }
            )

            OutlinedTextField(
                value = state.reviewScore.toString(),
                onValueChange = {
                    onEvent(BookReviewEvent.ScoreChange(it.toIntOrNull()?:0))
                },
                placeholder = {
                    Text(text = "Review Score")
                }
            )

            OutlinedTextField(
                value = state.comment,
                onValueChange = {
                    onEvent(BookReviewEvent.CommentChange(it))
                },
                placeholder = {
                    Text(text = "Comment")
                }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onEvent(BookReviewEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}