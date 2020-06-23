package com.android.noteapp.di

import android.app.Application
import androidx.room.Room
import com.android.noteapp.persistence.NoteDao
import com.android.noteapp.persistence.NoteDatabase
import com.android.noteapp.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesNoteDao(db: NoteDatabase): NoteDao {
        return db.noteDao()

    }

    @Singleton
    @Provides
    fun providesAppDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, "note_db").build()
    }

    @Singleton
    @Provides
    fun providesRepository(noteDao: NoteDao): Repository {
        return Repository(noteDao)
    }
}

