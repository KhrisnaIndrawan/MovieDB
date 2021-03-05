package com.khrisna.favorite.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.favorite.R
import com.khrisna.favorite.databinding.FragmentFavoriteMoviesBinding
import com.khrisna.favorite.ui.adapter.FavoritePagedAdapter
import com.khrisna.favorite.viewmodel.FavoritesViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory


class FavoriteMoviesFragment : Fragment() {

    private lateinit var model: com.khrisna.favorite.viewmodel.FavoritesViewModel
    private lateinit var favoriteListAdapter: FavoritePagedAdapter
    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding as FragmentFavoriteMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)

            model.getFavoritesAsPaged(true).observe(viewLifecycleOwner, { data ->
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

        binding.rvFavoriteMovies.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = favoriteListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): com.khrisna.favorite.viewmodel.FavoritesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(activity)

        return ViewModelProvider(this, factory)[FavoritesViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance(): FavoriteMoviesFragment {
            return FavoriteMoviesFragment()
        }
    }
}
