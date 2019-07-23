package com.khrisna.filmdb.ui.movies


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
import com.khrisna.filmdb.adapter.MovieListAdapter
import com.khrisna.filmdb.data.source.remote.response.MoviesResponse
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.viewmodel.MoviesViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    private lateinit var model: MoviesViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movies: MutableList<MoviesResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            model = obtainViewModel(activity as AppCompatActivity)
            if (model.nowPlaying == null) {
                model.getNowPlaying()
            }
            model.nowPlaying?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.upComing == null) {
                model.getUpComing()
            }
            model.upComing?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.popular == null) {
                model.getPopular()
            }
            model.popular?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.topRated == null) {
                model.getTopRate()
            }
            model.topRated?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = mutableListOf()
        movieListAdapter = MovieListAdapter(context as AppCompatActivity, movies)

        rv_movies.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context as AppCompatActivity, RecyclerView.VERTICAL, false)
            adapter = movieListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory(Injection.provideRepository())
        return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}
