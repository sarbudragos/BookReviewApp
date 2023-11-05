package com.example.myapplication.ui.screen.bookReviewList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.BookReview

@Composable
fun BookReviewListScreen(
    bookReviewList: List<BookReview>,
    onBookReviewClick: (BookReview) -> Unit,
    onAddBookReviewClick: () -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBookReviewClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "add book review"
                )
            }
        }
    ) {
        padding -> LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp + padding.calculateTopPadding(),
                bottom = 15.dp + padding.calculateBottomPadding()
            )
        ) {

            item {
                Text(text = "Book reviews",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        items(bookReviewList){ bookReview ->
            ListItem(
                headlineContent = {
                    Text(
                        text = bookReview.name +
                                ": " +bookReview.publishingYear.toString() + "\n" +
                                "By: " + bookReview.author + "\n" +
                                "Score: " + bookReview.reviewScore.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                supportingContent = {
                    Text(
                        text = bookReview.comment,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = Modifier.clickable(onClick = {
                    onBookReviewClick(bookReview)
                })
            )
        }

        }
    }
}