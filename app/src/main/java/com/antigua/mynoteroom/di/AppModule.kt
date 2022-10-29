package com.antigua.mynoteroom.di

import android.app.Application
import com.antigua.mynoteroom.data.AppDatabase
import com.antigua.mynoteroom.data.NoteDao
import com.antigua.mynoteroom.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository( noteDao: NoteDao ) : NoteRepository {
        return NoteRepository(noteDao = noteDao)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app : Application) : AppDatabase {
        return AppDatabase.getInstance(context = app)
    }

    @Singleton
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao{
        return  appDatabase.noteDao()
    }
}