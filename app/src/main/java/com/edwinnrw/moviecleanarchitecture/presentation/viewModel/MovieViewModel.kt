package com.edwinnrw.moviecleanarchitecture.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.edwinnrw.moviecleanarchitecture.domain.common.MovieUseCaseContract
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.edwinnrw.moviecleanarchitecture.domain.usecase.MovieUseCase
import com.edwinnrw.moviecleanarchitecture.presentation.common.getResultStateError
import javax.inject.Inject

class MovieViewModel@Inject constructor(private val movieUseCase: MovieUseCaseContract)  :  BaseViewModel() {

    val stateResultMovieNowPlaying = MutableLiveData<ResultState>()
    val stateResultMovieUpcoming= MutableLiveData<ResultState>()
    val stateResultMoviePopular = MutableLiveData<ResultState>()


    fun getMovieNowPlaying(){

        val disposable = movieUseCase.getMovieNowPlaying()
            .doOnSubscribe {
                stateResultMovieNowPlaying.postValue(ResultState.Loading)
            }
            .subscribe ({ resultState ->

                stateResultMovieNowPlaying.postValue(resultState)

            },{
                stateResultMovieNowPlaying.postValue(getResultStateError(it))

            })
        addDisposable(disposable)

    }

    fun getMovieUpcoming(){

        val disposable = movieUseCase.getMovieUpcoming()
            .doOnSubscribe {
                stateResultMovieUpcoming.postValue(ResultState.Loading)
            }
            .subscribe ({ resultState ->

                stateResultMovieUpcoming.postValue(resultState)

            },{
                stateResultMovieUpcoming.postValue(getResultStateError(it))

            })
        addDisposable(disposable)

    }

    fun getMovieTrending(){

        val disposable = movieUseCase.getMovieTrending()
            .doOnSubscribe {
                stateResultMoviePopular.postValue(ResultState.Loading)
            }
            .subscribe ({ resultState ->

                stateResultMoviePopular.postValue(resultState)

            },{
                stateResultMoviePopular.postValue(getResultStateError(it))

            })
        addDisposable(disposable)

    }

}