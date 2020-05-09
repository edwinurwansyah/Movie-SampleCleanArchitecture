package com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edwinnrw.moviecleanarchitecture.R
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity

class TrendingAdapter(val context: Context, private val items: MutableList<MoviesEntity>)
    : RecyclerView.Adapter<TrendingiVewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingiVewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_popular,parent,false)
        return TrendingiVewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingiVewHolder, position: Int) {
        holder.bindTo(items[position],context)
    }
}