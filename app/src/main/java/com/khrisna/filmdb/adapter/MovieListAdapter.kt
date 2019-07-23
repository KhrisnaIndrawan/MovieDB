package com.khrisna.filmdb.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse

class MovieListAdapter(
    private val context: Context, private val items: MutableList<MoviesResponse>,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) :
    RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    private lateinit var snapHelper: SnapHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_poster, parent, false)
        val viewHolder = MoviesViewHolder(view)
        snapHelper = GravitySnapHelper(Gravity.START)
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader = view.findViewById<TextView>(R.id.tv_header)
        private val rvPoster = view.findViewById<RecyclerView>(R.id.rv_poster)

        fun bind(item: MoviesResponse) {
            tvHeader.text = item.header

            val adapter = MovieAdapter(context as AppCompatActivity, item.movieResponses)
            rvPoster.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(context as AppCompatActivity, RecyclerView.HORIZONTAL, false)
                this.adapter = adapter
                setRecycledViewPool(rvViewPool)
            }
            snapHelper.attachToRecyclerView(rvPoster)
        }
    }
}