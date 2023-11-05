package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.bookReview.BookReviewScreen
import com.example.myapplication.ui.screen.bookReview.BookReviewViewModel
import com.example.myapplication.ui.screen.bookReviewList.BookReviewListScreen
import com.example.myapplication.ui.screen.bookReviewList.BookReviewListViewModel
import com.example.myapplication.ui.theme.BookReviewAppTheme
import com.example.myapplication.ui.util.Route
import com.example.myapplication.ui.util.UiEvent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookReviewAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Route.bookReviewList){
                    composable(route = Route.bookReviewList) {
                        val viewModel = hiltViewModel<BookReviewListViewModel>()
                        val bookReviewList by viewModel.bookReviewList.collectAsStateWithLifecycle()

                        BookReviewListScreen(
                            bookReviewList = bookReviewList,
                            onBookReviewClick = {
                                navController.navigate(
                                    Route.bookReview.replace(
                                        "{id}",
                                        it.id.toString()
                                    )
                                )
                            },
                            onAddBookReviewClick = {
                                navController.navigate(Route.bookReview)
                            }
                        )
                    }

                    composable(route = Route.bookReview) {
                        val viewModel = hiltViewModel<BookReviewViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }

                                    else -> Unit
                                }
                            }
                        }

                        BookReviewScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}