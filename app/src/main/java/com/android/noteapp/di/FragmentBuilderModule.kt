package com.android.noteapp.di

import com.android.noteapp.ui.AddFragment
import com.android.noteapp.ui.EditFragment
import com.android.noteapp.ui.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {


    @ContributesAndroidInjector
    abstract fun contributeAddFragment():AddFragment


    @ContributesAndroidInjector
    abstract fun contributeEditFragment():EditFragment


    @ContributesAndroidInjector
    abstract fun contributeListFragment():ListFragment
}