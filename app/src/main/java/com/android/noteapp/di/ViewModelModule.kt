package com.android.noteapp.di

import androidx.lifecycle.ViewModel
import com.android.noteapp.ui.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindMainViewModel(notesViewModel: NoteViewModel): ViewModel
}
