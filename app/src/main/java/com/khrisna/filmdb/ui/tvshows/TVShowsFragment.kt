package com.khrisna.filmdb.ui.tvshows


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.filmdb.R
import com.khrisna.filmdb.adapter.TVShowListAdapter
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tvshows.*


class TVShowsFragment : Fragment() {

    private lateinit var model: TVShowsViewModel
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var tvShows: MutableList<TVShowsEntity>

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
            model = obtainViewModel(activity as AppCompatActivity)
            if (model.airingToday == null) {
                model.getAiringToday()
            }
            model.airingToday?.observe(viewLifecycleOwner, Observer { data ->
                if (data != null) {
                    tvShows.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.onTheAir == null) {
                model.getOnTheAir()
            }
            model.onTheAir?.observe(viewLifecycleOwner, Observer { data ->
                if (data != null) {
                    tvShows.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.popular == null) {
                model.getPopular()
            }
            model.popular?.observe(viewLifecycleOwner, Observer { data ->
                if (data != null) {
                    tvShows.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
            if (model.topRated == null) {
                model.getTopRated()
            }
            model.topRated?.observe(viewLifecycleOwner, Observer { data ->
                if (data != null) {
                    tvShows.add(data)
                    tvShowListAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShows = mutableListOf()
        tvShowListAdapter = TVShowListAdapter(context as AppCompatActivity, tvShows)

        rv_tv_shows.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context as AppCompatActivity, RecyclerView.VERTICAL, false)
            adapter = tvShowListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): TVShowsViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory(Injection.provideRepository())
        return ViewModelProviders.of(activity, factory).get(TVShowsViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): TVShowsFragment {
            return TVShowsFragment()
        }
    }
}
