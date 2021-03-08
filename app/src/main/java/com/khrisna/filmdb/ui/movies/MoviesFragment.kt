package com.khrisna.filmdb.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Movies
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.FragmentMoviesBinding
import com.khrisna.filmdb.ui.adapter.movie.MovieListAdapter
import com.khrisna.filmdb.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private val model: MoviesViewModel by viewModel()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movies: MutableList<Movies>
    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding as FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model.getNowPlaying().observe(viewLifecycleOwner, { data ->
                data.let {
                    when (it) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Success -> {
                            if (it.data != null) {
                                movies.add(it.data as Movies)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getUpComing().observe(viewLifecycleOwner, { data ->
                data.let {
                    when (it) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Success -> {
                            if (it.data != null) {
                                movies.add(it.data as Movies)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getPopular().observe(viewLifecycleOwner, { data ->
                data.let {
                    when (it) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Success -> {
                            if (it.data != null) {
                                movies.add(it.data as Movies)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getTopRated().observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Error -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Success -> {
                            if (it.data != null) {
                                movies.add(it.data as Movies)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

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

        movies = mutableListOf()
        movieListAdapter = MovieListAdapter(context as AppCompatActivity)

        progressBar = view.findViewById(R.id.progressBar)

        binding.rvMovies.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieListAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}
