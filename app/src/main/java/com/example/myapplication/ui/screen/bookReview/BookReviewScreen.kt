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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            val showDeleteConfirmation = remember{mutableStateOf(false)}

            if(showDeleteConfirmation.value)
            {
                AlertDialog(
                    title = {
                        Text(text = "Delete?")
                    },
                    text = {
                        Text(text = "Are you sure?")
                    },
                    onDismissRequest = {
                        showDeleteConfirmation.value = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                onEvent(BookReviewEvent.DeleteBookReview)
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDeleteConfirmation.value = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    })
            }

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
                            showDeleteConfirmation.value = true

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

            val showValidationError = remember{mutableStateOf(false)}

            if(showValidationError.value)
            {
                AlertDialog(
                    title = {
                        Text(text = "Validation error?")
                    },
                    text = {
                        Text(text = "A field is either empty or below 0")
                    },
                    onDismissRequest = {
                        showValidationError.value = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showValidationError.value = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    }
                    )
            }


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
                        if(state.name == "" || state.author == "" || state.publishingYear!! < 0 ||
                            state.reviewScore!! < 0 || state.reviewScore > 10){
                            showValidationError.value = true

                        }
                        else
                        {
                            onEvent(BookReviewEvent.Save)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}