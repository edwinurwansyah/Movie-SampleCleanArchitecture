package com.edwinnrw.moviecleanarchitecture

import android.app.Application
import androidx.multidex.MultiDex
import com.edwinnrw.moviecleanarchitecture.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication :Application(), HasAndroidInjector {
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>



    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }

}