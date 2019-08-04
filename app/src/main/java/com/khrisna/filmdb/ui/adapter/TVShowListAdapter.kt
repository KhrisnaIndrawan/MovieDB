package com.khrisna.filmdb.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.ui.pagelist.ViewAllActivity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper as GravitySnapHelper1

class TVShowListAdapter(
    private val context: Context,
    private val rvViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) :
    ListAdapter<TVShowsEntity, TVShowListAdapter.TVShowsViewHolder>(
        object : DiffUtil.ItemCallback<TVShowsEntity>() {
            override fun areItemsTheSame(oldItem: TVShowsEntity, newItem: TVShowsEntity): Boolean {
                return oldItem.header == newItem.header
            }

            override fun areContentsTheSame(oldItem: TVShowsEntity, newItem: TVShowsEntity): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    private lateinit var snapHelper: SnapHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_poster, parent, false)
        val viewHolder = TVShowsViewHolder(view)
        snapHelper = GravitySnapHelper1(Gravity.START)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TVShowsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader = view.findViewById<TextView>(R.id.tv_header)
        private val rvPoster = view.findViewById<RecyclerView>(R.id.rv_poster)
        private val tvViewAll = view.findViewById<TextView>(R.id.tv_see_more)

        fun bind(item: TVShowsEntity) {
            tvHeader.text = item.header

            val adapter = TVShowAdapter(context as AppCompatActivity)
            adapter.submitList(item.tvShows)

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
                intent.putExtra(ViewAllActivity.EXTRA_IS_MOVIE, false)
                intent.putExtra(ViewAllActivity.EXTRA_HEADER, item.header)

                it.context.startActivity(intent)
            }
        }
    }
}