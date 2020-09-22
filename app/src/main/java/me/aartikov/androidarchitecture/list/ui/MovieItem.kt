package me.aartikov.androidarchitecture.list.ui

import android.util.Log
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.*
import me.aartikov.androidarchitecture.R
import me.aartikov.androidarchitecture.list.domain.Movie

class MovieItem(private val movie: Movie) : Item() {

    override fun getLayout() = R.layout.item_movie

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.id.text = movie.id.toString()
        viewHolder.title.text = movie.title
        viewHolder.overview.text = movie.overview
    }

    override fun getId(): Long = movie.id.toLong()
}

fun List<Movie>.toGroupieItems() : List<MovieItem>
        = this.map { MovieItem(it) }