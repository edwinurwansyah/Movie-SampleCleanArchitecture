package com.edwinnrw.moviecleanarchitecture.domain

import com.edwinnrw.moviecleanarchitecture.data.common.MovieRepositoryContract
import com.edwinnrw.moviecleanarchitecture.data.entities.BaseMovieDataSourceApi
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.edwinnrw.moviecleanarchitecture.domain.mapper.movieMapListToEntity
import com.edwinnrw.moviecleanarchitecture.domain.usecase.MovieUseCase
import com.edwinnrw.moviecleanarchitecture.helper.InstantRuleExecution
import com.edwinnrw.moviecleanarchitecture.helper.TrampolineSchedulerRX
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@RunWith(JUnitPlatform::class)
internal class AccountUseCaseTest : Spek({
    Feature("movie") {
        val movieRepository = mock<MovieRepositoryContract>()
        val movieUseCase = MovieUseCase(movieRepository)

        beforeFeature {
            TrampolineSchedulerRX.start()
            InstantRuleExecution.start()
        }

        Scenario("get movie now playing, response success") {

            val expectedResult = BaseMovieDataSourceApi(0, mutableListOf(),10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMovieNowPlaying()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Now Playing") {
                testSubscribe = movieUseCase.getMovieNowPlaying().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }
        Scenario("get movie now playing, response success with return data null") {

            val expectedResult = BaseMovieDataSourceApi(0, null,10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMovieNowPlaying()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Now Playing") {
                testSubscribe = movieUseCase.getMovieNowPlaying().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }

        Scenario("get movie now upcoming, response success") {

            val expectedResult = BaseMovieDataSourceApi(0, mutableListOf(),10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMovieUpcoming()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Upcoming") {
                testSubscribe = movieUseCase.getMovieUpcoming().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }
        Scenario("get movie now upcoming, response success with return data null") {

            val expectedResult = BaseMovieDataSourceApi(0, null,10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMovieUpcoming()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Upcoming") {
                testSubscribe = movieUseCase.getMovieUpcoming().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }

        Scenario("get movie now trending, response success") {

            val expectedResult = BaseMovieDataSourceApi(0, mutableListOf(),10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMoviePopular()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Trending") {
                testSubscribe = movieUseCase.getMovieTrending().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }
        Scenario("get movie now trending, response success with return data null") {

            val expectedResult = BaseMovieDataSourceApi(0, null,10,100)
            var testSubscribe: TestObserver<ResultState> = TestObserver()

            Given("set expected result from repository") {

                given(movieRepository.getMoviePopular()).willReturn(Observable.just(expectedResult))

            }

            When("Request Api Movie Trending") {
                testSubscribe = movieUseCase.getMovieTrending().test()
            }

            Then("Result Success"){
                testSubscribe.assertNoErrors()
                testSubscribe.assertComplete()
                testSubscribe.assertValues(ResultState.Success(
                    movieMapListToEntity(expectedResult.moviesDataSourceApis?: mutableListOf()),""))
            }

        }


        afterFeature {
            TrampolineSchedulerRX.tearDown()
            InstantRuleExecution.tearDown()
        }

    }
})