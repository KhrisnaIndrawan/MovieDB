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
import com.khrisna.core.data.source.local.entity.TVShowsEntity
import com.khrisna.core.data.source.vo.Status
import com.khrisna.core.di.Injection
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.FragmentTvshowsBinding
import com.khrisna.filmdb.ui.adapter.tvshow.TVShowListAdapter
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory


class TVShowsFragment : Fragment() {

    private lateinit var model: TVShowsViewModel
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var tvShows: MutableList<TVShowsEntity>
    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentTvshowsBinding? = null
    private val binding get() = _binding as FragmentTvshowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvshowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)
            model.getAiringToday().observe(viewLifecycleOwner, Observer { data ->
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
                                tvShowListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getOnTheAir().observe(viewLifecycleOwner, Observer { data ->
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
                                tvShowListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getPopular().observe(viewLifecycleOwner, Observer { data ->
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
                                tvShowListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getTopRated().observe(viewLifecycleOwner, Observer { data ->
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
                                tvShowListAdapter.notifyDataSetChanged()

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

        binding.rvTvshows.apply {
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
