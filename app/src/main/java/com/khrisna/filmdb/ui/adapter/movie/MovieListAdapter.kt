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
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.MoviesEntity
import com.khrisna.filmdb.ui.pagelist.ViewAllActivity
import com.khrisna.filmdb.ui.pagelist.ViewAllActivity.Companion.EXTRA_HEADER
import com.khrisna.filmdb.ui.pagelist.ViewAllActivity.Companion.EXTRA_IS_MOVIE

class MovieListAdapter(
    private val context: Context,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) :
    ListAdapter<MoviesEntity, MovieListAdapter.MoviesViewHolder>(
        object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.header == newItem.header
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
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

        fun bind(item: MoviesEntity) {
            tvHeader.text = item.header

            val adapter = MovieAdapter(context as AppCompatActivity)
            adapter.submitList(item.movies)

            rvPoster.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(context as AppCompatActivity, RecyclerView.HORIZONTAL, false)
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