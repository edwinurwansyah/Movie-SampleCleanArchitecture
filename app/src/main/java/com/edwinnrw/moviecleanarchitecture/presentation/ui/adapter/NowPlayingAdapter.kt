package com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinnrw.moviecleanarchitecture.R
import com.edwinnrw.moviecleanarchitecture.databinding.ItemNowWatchingBinding
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity

class NowPlayingAdapter(val context: Context, private val items: MutableList<MoviesEntity>)
    : RecyclerView.Adapter<NowPlayingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val binding = ItemNowWatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bindTo(items[position],context)
    }
}