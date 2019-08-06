package com.khrisna.filmdb.ui.tvshows


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.TVShowsEntity
import com.khrisna.filmdb.data.source.vo.Status
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.ui.adapter.TVShowListAdapter
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tvshows.*


class TVShowsFragment : Fragment() {

    private lateinit var model: TVShowsViewModel
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var tvShows: MutableList<TVShowsEntity>
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)
            if (model.airingToday == null) {
                model.getAiringToday()
            }
            model.airingToday?.observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get tv shows fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                tvShows.add(it.data as TVShowsEntity)
                                tvShowListAdapter.submitList(tvShows)

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            if (model.onTheAir == null) {
                model.getOnTheAir()
            }
            model.onTheAir?.observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get tv shows fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                tvShows.add(it.data as TVShowsEntity)
                                tvShowListAdapter.submitList(tvShows)

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            if (model.popular == null) {
                model.getPopular()
            }
            model.popular?.observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get tv shows fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                tvShows.add(it.data as TVShowsEntity)
                                tvShowListAdapter.submitList(tvShows)

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            if (model.topRated == null) {
                model.getTopRated()
            }
            model.topRated?.observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get tv shows fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                tvShows.add(it.data as TVShowsEntity)
                                tvShowListAdapter.submitList(tvShows)

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShows = mutableListOf()
        tvShowListAdapter = TVShowListAdapter(context as AppCompatActivity)

        progressBar = view.findViewById(R.id.progressBar)

        rv_tv_shows.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context as AppCompatActivity, RecyclerView.VERTICAL, false)
            adapter = tvShowListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): TVShowsViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))
        return ViewModelProviders.of(activity, factory).get(TVShowsViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): TVShowsFragment {
            return TVShowsFragment()
        }
    }
}
