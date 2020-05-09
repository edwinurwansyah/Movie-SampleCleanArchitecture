package com.edwinnrw.moviecleanarchitecture.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinnrw.moviecleanarchitecture.R
import com.edwinnrw.moviecleanarchitecture.domain.common.ResultState
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity
import com.edwinnrw.moviecleanarchitecture.presentation.common.PeekingLiniearLayoutManager
import com.edwinnrw.moviecleanarchitecture.presentation.common.observe
import com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter.NowPlayingAdapter
import com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter.TrendingAdapter
import com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter.UpcomingAdapter
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.MovieViewModel
import com.edwinnrw.moviecleanarchitecture.presentation.viewModel.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieViewModel

    lateinit var adapterNowPlaying:NowPlayingAdapter
    lateinit var adapteTrending:TrendingAdapter
    lateinit var adapterUpcoming:UpcomingAdapter

    lateinit var itemsNowWatching:MutableList<MoviesEntity>
    lateinit var itemsUpcoming:MutableList<MoviesEntity>
    lateinit var itemsTrending:MutableList<MoviesEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        observe(viewModel.stateResultMovieNowPlaying,::manageStateResponseNowPlaying)
        observe(viewModel.stateResultMoviePopular,::manageStateResponseTrending)
        observe(viewModel.stateResultMovieUpcoming,::manageStateResponseUpcoming)

        itemsNowWatching = mutableListOf()
        itemsTrending = mutableListOf()
        itemsUpcoming = mutableListOf()

        adapterNowPlaying = NowPlayingAdapter(this,itemsNowWatching)
        adapteTrending = TrendingAdapter(this,itemsTrending)
        adapterUpcoming = UpcomingAdapter(this,itemsUpcoming)


        recyclerViewTrending.apply {
            layoutManager = PeekingLiniearLayoutManager(this@MainActivity,0.8f,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapteTrending
        }

        recyclerViewUpcoming.apply {
            layoutManager = PeekingLiniearLayoutManager(this@MainActivity,0.38f,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterUpcoming
        }
        recyclerViewTheatre.apply {
            layoutManager = PeekingLiniearLayoutManager(this@MainActivity,0.38f,LinearLayoutManager.HORIZONTAL,false)
            adapter = adapterNowPlaying
        }
        viewModel.getMovieNowPlaying()
        viewModel.getMovieTrending()
        viewModel.getMovieUpcoming()

    }
    private fun manageStateResponseTrending(state: ResultState){
        when(state){
            is ResultState.Success<*> ->{
                var data = state.data as List<MoviesEntity>
                itemsTrending.addAll(data)
                adapteTrending.notifyDataSetChanged()

            }
            is ResultState.Loading ->{

            }
            is ResultState.NoConnection ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()

            }
            is ResultState.BadRequest ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun manageStateResponseNowPlaying(state: ResultState){
        when(state){
            is ResultState.Success<*> ->{
                var data = state.data as List<MoviesEntity>
                itemsNowWatching.addAll(data)
                adapterNowPlaying.notifyDataSetChanged()

            }
            is ResultState.Loading ->{

            }
            is ResultState.NoConnection ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()

            }
            is ResultState.BadRequest ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()
            }

        }
    }
    private fun manageStateResponseUpcoming(state: ResultState){
        when(state){
            is ResultState.Success<*> ->{
                var data = state.data as List<MoviesEntity>
                itemsUpcoming.addAll(data)
                adapterUpcoming.notifyDataSetChanged()

            }
            is ResultState.Loading ->{

            }
            is ResultState.NoConnection ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()

            }
            is ResultState.BadRequest ->{
                Toast.makeText(this,state.throwable.message, Toast.LENGTH_LONG).show()
            }

        }
    }
}
