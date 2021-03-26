package com.khrisna.favorite.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.favorite.R
import com.khrisna.favorite.databinding.FragmentFavoriteMoviesBinding
import com.khrisna.favorite.ui.adapter.FavoritePagedAdapter
import com.khrisna.favorite.viewmodel.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteMoviesFragment : Fragment() {

    private val model: FavoriteViewModel by viewModel()
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
        activity.let { _ ->

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

    companion object {

        @JvmStatic
        fun newInstance(): FavoriteMoviesFragment {
            return FavoriteMoviesFragment()
        }
    }
}
