package com.android.noteapp.di

import com.android.noteapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class,ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}