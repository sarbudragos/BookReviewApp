package com.example.myapplication.data.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.BookReviewDatabase
import com.example.myapplication.data.repository.BookReviewRepositoryImplementation
import com.example.myapplication.domain.repository.BookReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBookReviewDatabase(@ApplicationContext context: Context): BookReviewDatabase =
        Room.databaseBuilder(
            context,
            BookReviewDatabase::class.java,
            BookReviewDatabase.name
        ).build()
    @Provides
    @Singleton
    fun provideBookReviewRepository(database: BookReviewDatabase): BookReviewRepository =
        BookReviewRepositoryImplementation(database.dao)
}