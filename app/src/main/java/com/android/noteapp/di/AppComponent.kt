package com.android.noteapp.di

import android.app.Application
import com.android.noteapp.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,ActivityBuilderModule::class,ViewModelFactoryModule::class])
interface AppComponent:AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder{
        fun build():AppComponent

        @BindsInstance
        fun application(application: Application):Builder
    }
}