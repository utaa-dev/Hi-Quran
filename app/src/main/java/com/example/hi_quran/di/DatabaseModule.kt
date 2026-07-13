package com.example.hi_quran.di

import android.content.Context
import androidx.room.Room
import com.example.hi_quran.data.local.dao.*
import com.example.hi_quran.data.local.database.HiQuranDatabase
import com.example.hi_quran.data.repository.QuranRepositoryImpl
import com.example.hi_quran.domain.repository.QuranRepository
import com.example.hi_quran.domain.usecase.bookmark.AddBookmarkUseCase
import com.example.hi_quran.domain.usecase.bookmark.GetBookmarksUseCase
import com.example.hi_quran.domain.usecase.juz.GetJuzDetailUseCase
import com.example.hi_quran.domain.usecase.juz.GetJuzUseCase
import com.example.hi_quran.domain.usecase.lastread.GetLastReadUseCase
import com.example.hi_quran.domain.usecase.lastread.UpdateLastReadUseCase
import com.example.hi_quran.domain.usecase.quran.GetAyahsBySurahUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahByNumberUseCase
import com.example.hi_quran.domain.usecase.quran.GetSurahsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HiQuranDatabase {
        return Room.databaseBuilder(
            context,
            HiQuranDatabase::class.java,
            HiQuranDatabase.DATABASE_NAME
        ).createFromAsset("database/quran.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideQuranDao(database: HiQuranDatabase): QuranDao = database.quranDao()

    @Provides
    @Singleton
    fun provideJuzDao(database: HiQuranDatabase): JuzDao = database.juzDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(database: HiQuranDatabase): BookmarkDao = database.bookmarkDao()

    @Provides
    @Singleton
    fun provideLastReadDao(database: HiQuranDatabase): LastReadDao = database.lastReadDao()

    @Provides
    @Singleton
    fun provideDoaDao(database: HiQuranDatabase): DoaDao = database.doaDao()

    @Provides
    @Singleton
    fun provideQuranRepository(
        quranDao: QuranDao,
        juzDao: JuzDao,
        bookmarkDao: BookmarkDao,
        lastReadDao: LastReadDao,
        doaDao: DoaDao
    ): QuranRepository {
        return QuranRepositoryImpl(quranDao, juzDao, bookmarkDao, lastReadDao, doaDao)
    }

    @Provides
    @Singleton
    fun provideGetSurahsUseCase(repository: QuranRepository): GetSurahsUseCase {
        return GetSurahsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetJuzUseCase(repository: QuranRepository): GetJuzUseCase {
        return GetJuzUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetJuzDetailUseCase(repository: QuranRepository): GetJuzDetailUseCase {
        return GetJuzDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetBookmarksUseCase(repository: QuranRepository): GetBookmarksUseCase {
        return GetBookmarksUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddBookmarkUseCase(repository: QuranRepository): AddBookmarkUseCase {
        return AddBookmarkUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetLastReadUseCase(repository: QuranRepository): GetLastReadUseCase {
        return GetLastReadUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateLastReadUseCase(repository: QuranRepository): UpdateLastReadUseCase {
        return UpdateLastReadUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAyahsBySurahUseCase(repository: QuranRepository): GetAyahsBySurahUseCase {
        return GetAyahsBySurahUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSurahByNumberUseCase(repository: QuranRepository): GetSurahByNumberUseCase {
        return GetSurahByNumberUseCase(repository)
    }
}
