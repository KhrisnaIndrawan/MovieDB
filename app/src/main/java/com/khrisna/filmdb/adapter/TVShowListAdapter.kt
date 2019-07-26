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
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper as GravitySnapHelper1

class TVShowListAdapter(
    private val context: Context, private val items: MutableList<TVShowsEntity>,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) :
    RecyclerView.Adapter<TVShowListAdapter.TVShowsViewHolder>() {

    private lateinit var snapHelper: SnapHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_poster, parent, false)
        val viewHolder = TVShowsViewHolder(view)
        snapHelper = GravitySnapHelper1(Gravity.START)
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class TVShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader = view.findViewById<TextView>(R.id.tv_header)
        private val rvPoster = view.findViewById<RecyclerView>(R.id.rv_poster)

        fun bind(item: TVShowsEntity) {
            tvHeader.text = item.header

            val adapter = item.tvShow?.let { TVShowAdapter(context as AppCompatActivity, it) }
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