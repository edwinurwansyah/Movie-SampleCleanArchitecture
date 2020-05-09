package com.edwinnrw.moviecleanarchitecture.presentation.ui.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.edwinnrw.moviecleanarchitecture.domain.entities.MoviesEntity
import com.edwinnrw.moviecleanarchitecture.presentation.common.UIUtils
import kotlinx.android.synthetic.main.item_upcoming.view.*

class UpcomingVewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(item: MoviesEntity, context: Context) {

        itemView.apply {
            var requestOptions = RequestOptions().transforms(RoundedCorners(9))

            Glide.with(context).asBitmap()
                .load("http://image.tmdb.org/t/p/w500"+item.posterPath)
                .apply(requestOptions)
                .into(UIUtils.getRoundedImageTarget(context, itemImageMovie, 20f))
        }

    }
}