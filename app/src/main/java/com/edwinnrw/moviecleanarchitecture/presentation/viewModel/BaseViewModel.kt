package com.edwinnrw.moviecleanarchitecture.presentation.viewModel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher

open class BaseViewModel : ViewModel() {

    private  val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun  addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
    private fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposable()
    }
}