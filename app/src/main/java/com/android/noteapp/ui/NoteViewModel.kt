package com.android.noteapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.noteapp.persistence.Note
import com.android.noteapp.repository.Repository
import javax.inject.Inject

class NoteViewModel @Inject constructor(val repository:Repository):ViewModel() {
    fun insert(note: Note) {
        return repository.insert(note)

    }
    fun delete(note: Note) {
        return repository.delete(note)

    }
    fun deleteById(id:Int) {
        return repository.deleteById(id)

    }
    fun update(note: Note) {
        return repository.update(note)

    }
    fun getAllNotes():LiveData<List<Note>>{
        return repository.getAllNotes()
    }
}