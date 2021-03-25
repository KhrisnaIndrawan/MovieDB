package com.khrisna.filmdb.ui.tvshows


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.TVShows
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.FragmentTvshowsBinding
import com.khrisna.filmdb.ui.adapter.tvshow.TVShowListAdapter
import com.khrisna.filmdb.viewmodel.TVShowsViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TVShowsFragment : Fragment() {

    private val model: TVShowsViewModel by viewModel()
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var tvShows: MutableList<TVShows>
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
        activity.let {
            model.getAiringToday().observe(viewLifecycleOwner, { data ->
                showData(data)
            })
            model.getOnTheAir().observe(viewLifecycleOwner, { data ->
                showData(data)
            })
            model.getPopular().observe(viewLifecycleOwner, { data ->
                showData(data)
            })
            model.getTopRated().observe(viewLifecycleOwner, { data ->
                showData(data)
            })
        }
    }

    private fun showData(data: Resource<TVShows>?) {
        data.let {
            when (it) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        "Get tv shows fail, please check your internet connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        tvShows.add(it.data as TVShows)
                        tvShowListAdapter.submitList(tvShows)

                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
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

    companion object {

        @JvmStatic
        fun newInstance(): TVShowsFragment {
            return TVShowsFragment()
        }
    }
}
