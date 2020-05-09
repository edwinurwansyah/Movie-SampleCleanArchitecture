package com.edwinnrw.moviecleanarchitecture.presentation

import androidx.lifecycle.Observer
import com.edwinnrw.moviecleanarchitecture.data.entities.BaseErrorDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.common.MovieUseCaseContract
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.edwinnrw.moviecleanarchitecture.domain.entities.BaseMovieEntity
import com.edwinnrw.moviecleanarchitecture.helper.InstantRuleExecution
import com.edwinnrw.moviecleanarchitecture.helper.TrampolineSchedulerRX
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.MovieViewModel
import com.google.gson.Gson
import com.jraska.livedata.test
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnitPlatform::class)
internal class MovieViewModelTest : Spek({

    Feature("Login") {
        val useCase = mock<MovieUseCaseContract>()
        val viewModel = MovieViewModel(useCase)
        val observerNowPlaying = mock<Observer<ResultState>>()
        val observerUpcoming = mock<Observer<ResultState>>()
        val observerTrending = mock<Observer<ResultState>>()


        beforeFeature {
            TrampolineSchedulerRX.start()
            InstantRuleExecution.start()
            viewModel.stateResultMovieNowPlaying.observeForever(observerNowPlaying)
            viewModel.stateResultMovieUpcoming.observeForever(observerUpcoming)
            viewModel.stateResultMoviePopular.observeForever(observerTrending)

        }
        Scenario("get movie now playing, response success") {
            val expectedResult = BaseMovieEntity(0, mutableListOf(),10,100)
            var expectedResultState: ResultState

            Given("set expected resul state") {

                expectedResultState = ResultState.Success(expectedResult.moviesDataSourceApis, "")


                given(useCase.getMovieNowPlaying()).willReturn(Observable.just(expectedResultState))

            }

            When("request api movie now playing") {
                viewModel.getMovieNowPlaying()
            }

            Then("result success and return data") {
                verify(observerNowPlaying, atLeastOnce()).onChanged(ResultState.Loading)
                verify(observerNowPlaying, atLeastOnce()).onChanged(ResultState.Success(expectedResult.moviesDataSourceApis, ""))
            }
        }
        Scenario("get movie now playing, response error") {


            val baseDataSourceApi = BaseErrorDataSourceApi(400, "Bad Request!", false)
            val responseBody: Response<BaseErrorDataSourceApi> = Response.error(400, Gson().toJson(baseDataSourceApi).toString().toResponseBody())
            val errorExpected = HttpException(responseBody)
            Given("Input email (mail@mail.com), deviceId(awq13vsf),and password (password)") {

                Mockito.`when`(useCase.getMovieNowPlaying())
                    .thenReturn(Observable.error(errorExpected))

            }

            When("Request Api Login") {
                viewModel.getMovieNowPlaying()
            }

            Then("Result Bad Request and Return Error Response") {
                verify(observerNowPlaying, atLeastOnce()).onChanged(ResultState.Loading)
                viewModel.stateResultMovieNowPlaying.test().assertValue {
                    it is ResultState.BadRequest
                }
            }
        }

        Scenario("get movie upcoming, response success") {
            val expectedResult = BaseMovieEntity(0, mutableListOf(),10,100)
            var expectedResultState: ResultState

            Given("set expected resul state") {

                expectedResultState = ResultState.Success(expectedResult.moviesDataSourceApis, "")


                given(useCase.getMovieUpcoming()).willReturn(Observable.just(expectedResultState))

            }

            When("request api movie upcoming") {
                viewModel.getMovieUpcoming()
            }

            Then("result success and return data") {
                verify(observerUpcoming, atLeastOnce()).onChanged(ResultState.Loading)
                verify(observerUpcoming, atLeastOnce()).onChanged(ResultState.Success(expectedResult.moviesDataSourceApis, ""))
            }
        }
        Scenario("get movie upcoming, response error") {


            val baseDataSourceApi = BaseErrorDataSourceApi(400, "Bad Request!", false)
            val responseBody: Response<BaseErrorDataSourceApi> = Response.error(400, Gson().toJson(baseDataSourceApi).toString().toResponseBody())
            val errorExpected = HttpException(responseBody)
            Given("set error expected from use case") {

                Mockito.`when`(useCase.getMovieUpcoming())
                    .thenReturn(Observable.error(errorExpected))

            }

            When("request api upcoming") {
                viewModel.getMovieUpcoming()
            }

            Then("result Bad Request") {
                verify(observerNowPlaying, atLeastOnce()).onChanged(ResultState.Loading)
                viewModel.stateResultMovieUpcoming.test().assertValue {
                    it is ResultState.BadRequest
                }
            }
        }

        Scenario("get movie trending, response success") {
            val expectedResult = BaseMovieEntity(0, mutableListOf(),10,100)
            var expectedResultState: ResultState

            Given("set expected resul state") {

                expectedResultState = ResultState.Success(expectedResult.moviesDataSourceApis, "")


                given(useCase.getMovieTrending()).willReturn(Observable.just(expectedResultState))

            }

            When("request api movie trending") {
                viewModel.getMovieTrending()
            }

            Then("result success and return data") {
                verify(observerUpcoming, atLeastOnce()).onChanged(ResultState.Loading)
                verify(observerUpcoming, atLeastOnce()).onChanged(ResultState.Success(expectedResult.moviesDataSourceApis, ""))
            }
        }
        Scenario("get movie trending, response error") {


            val baseDataSourceApi = BaseErrorDataSourceApi(400, "Bad Request!", false)
            val responseBody: Response<BaseErrorDataSourceApi> = Response.error(400, Gson().toJson(baseDataSourceApi).toString().toResponseBody())
            val errorExpected = HttpException(responseBody)
            Given("set error expected from use case") {

                Mockito.`when`(useCase.getMovieTrending())
                    .thenReturn(Observable.error(errorExpected))

            }

            When("request api trending") {
                viewModel.getMovieTrending()
            }

            Then("result Bad Request") {
                verify(observerNowPlaying, atLeastOnce()).onChanged(ResultState.Loading)
                viewModel.stateResultMovieUpcoming.test().assertValue {
                    it is ResultState.BadRequest
                }
            }
        }
        afterFeature {
            TrampolineSchedulerRX.tearDown()
            InstantRuleExecution.tearDown()
        }
    }
})