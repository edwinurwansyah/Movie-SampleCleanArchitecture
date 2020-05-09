package com.edwinnrw.moviecleanarchitecture.presentation.di


import com.edwinnrw.moviecleanarchitecture.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity


}