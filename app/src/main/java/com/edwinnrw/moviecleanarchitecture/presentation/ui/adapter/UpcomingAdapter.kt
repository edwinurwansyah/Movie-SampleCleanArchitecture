package com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinnrw.moviecleanarchitecture.R
import com.edwinnrw.moviecleanarchitecture.databinding.ItemUpcomingBinding
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity

class UpcomingAdapter(val context: Context, private val items: MutableList<MoviesEntity>)
    : RecyclerView.Adapter<UpcomingVewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingVewHolder {
        val binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingVewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UpcomingVewHolder, position: Int) {
        holder.bindTo(items[position],context)
    }
}