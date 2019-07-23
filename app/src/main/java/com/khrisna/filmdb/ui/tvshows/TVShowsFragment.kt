package com.khrisna.filmdb.ui.tvshows


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.filmdb.R
import com.khrisna.filmdb.adapter.TVShowListAdapter
import com.khrisna.filmdb.data.source.remote.response.TVShowsResponse
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import kotlinx.android.synthetic.main.fragment_tvshows.*


class TVShowsFragment : Fragment() {

    private lateinit var model: TVShowsViewModel
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var tvShowResponses: MutableList<TVShowsResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            model = ViewModelProviders.of(this).get(TVShowsViewModel::class.java)
            if (model.airingToday == null) {
                model.getAiringToday()
            }
            model.airingToday?.observe(this, Observer { data ->
                if (data != null) {
                    tvShowResponses.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.onTheAir == null) {
                model.getOnTheAir()
            }
            model.onTheAir?.observe(this, Observer { data ->
                if (data != null) {
                    tvShowResponses.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.popular == null) {
                model.getPopular()
            }
            model.popular?.observe(this, Observer { data ->
                if (data != null) {
                    tvShowResponses.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.topRated == null) {
                model.getTopRate()
            }
            model.topRated?.observe(this, Observer { data ->
                if (data != null) {
                    tvShowResponses.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowResponses = mutableListOf()
        tvShowListAdapter = TVShowListAdapter(context as AppCompatActivity, tvShowResponses)

        rv_tv_shows.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context as AppCompatActivity, RecyclerView.VERTICAL, false)
            adapter = tvShowListAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): TVShowsFragment {
            return TVShowsFragment()
        }
    }
}
