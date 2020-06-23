package com.android.noteapp


import com.android.noteapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication:DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return DaggerAppComponent.builder().application(this).build()
    }
}
