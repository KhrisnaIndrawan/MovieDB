package com.khrisna.filmdb.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.filmdb.R
import com.khrisna.core.di.Injection
import com.khrisna.filmdb.ui.adapter.favorite.FavoritePagedAdapter
import com.khrisna.filmdb.viewmodel.FavoritesViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_tv_shows.*


class FavoriteMoviesFragment : Fragment() {

    private lateinit var model: FavoritesViewModel
    private lateinit var favoriteListAdapter: FavoritePagedAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)

            model.getFavoritesAsPaged(true).observe(viewLifecycleOwner, Observer { data ->
                data.let {
                    favoriteListAdapter.submitList(it)
                    favoriteListAdapter.notifyDataSetChanged()

                    progressBar.visibility = View.INVISIBLE
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteListAdapter = FavoritePagedAdapter(context as AppCompatActivity)

        progressBar = view.findViewById(R.id.progressBar)

        rv_favorites.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = favoriteListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): FavoritesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))
        return ViewModelProviders.of(activity, factory).get(FavoritesViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): FavoriteMoviesFragment {
            return FavoriteMoviesFragment()
        }
    }
}
