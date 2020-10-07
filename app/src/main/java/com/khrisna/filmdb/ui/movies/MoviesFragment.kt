package com.khrisna.filmdb.ui.movies


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
import com.khrisna.core.data.source.local.entity.MoviesEntity
import com.khrisna.core.data.source.vo.Status
import com.khrisna.core.di.Injection
import com.khrisna.filmdb.ui.adapter.movie.MovieListAdapter
import com.khrisna.filmdb.viewmodel.MoviesViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    private lateinit var model: MoviesViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movies: MutableList<MoviesEntity>
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)
            model.getNowPlaying().observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                movies.add(it.data as MoviesEntity)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

                                progressBar.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
            model.getUpComing().observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                movies.add(it.data as MoviesEntity)
                                movieListAdapter.submitList(movies)
                                movieListAdapter.notifyDataSetChanged()

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
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                movies.add(it.data as MoviesEntity)
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
                    when (it.status) {
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Get movies fail, please check your internet connection!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Status.SUCCESS -> {
                            if (it.data != null) {
                                movies.add(it.data as MoviesEntity)
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

        rv_movies.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))
        return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}
