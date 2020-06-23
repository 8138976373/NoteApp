package com.android.noteapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.noteapp.persistence.Note
import com.android.noteapp.persistence.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }
    }

    fun delete(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.delete(note)
        }
    }

    fun deleteById(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteById(id)
        }
    }

    fun update(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.update(note)
            Log.e("DEBUG","update is called in repo")
        }
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }
}