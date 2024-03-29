package com.khrisna.filmdb.ui.adapter.movie

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.khrisna.core.domain.model.Movies
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.viewall.ViewAllActivity
import com.khrisna.filmdb.ui.viewall.ViewAllActivity.Companion.EXTRA_HEADER
import com.khrisna.filmdb.ui.viewall.ViewAllActivity.Companion.EXTRA_IS_MOVIE

class MovieListAdapter(
    private val context: Context,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) :
    ListAdapter<Movies, MovieListAdapter.MoviesViewHolder>(
        object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.header == newItem.header
                        && oldItem.movies == newItem.movies
            }
        }
    ) {

    private lateinit var snapHelper: SnapHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_poster, parent, false)
        val viewHolder = MoviesViewHolder(view)
        snapHelper = GravitySnapHelper(Gravity.START)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader = view.findViewById<TextView>(R.id.tv_header)
        private val rvPoster = view.findViewById<RecyclerView>(R.id.rv_poster)
        private val tvViewAll = view.findViewById<TextView>(R.id.tv_see_more)

        fun bind(item: Movies) {
            tvHeader.text = item.header

            val adapter = MovieAdapter(context as AppCompatActivity, false)
            adapter.submitList(item.movies)

            rvPoster.apply {
                layoutManager =
                    LinearLayoutManager(
                        context as AppCompatActivity,
                        RecyclerView.HORIZONTAL,
                        false
                    )
                this.adapter = adapter
                setRecycledViewPool(rvViewPool)
            }
            snapHelper.attachToRecyclerView(rvPoster)

            tvViewAll.setOnClickListener {

                val intent = Intent(context, ViewAllActivity::class.java)
                intent.putExtra(EXTRA_IS_MOVIE, true)
                intent.putExtra(EXTRA_HEADER, item.header)

                it.context.startActivity(intent)
            }
        }
    }
}