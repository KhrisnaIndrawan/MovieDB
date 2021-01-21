package com.khrisna.favorite.ui


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
import com.khrisna.core.di.Injection
import com.khrisna.favorite.R
import com.khrisna.favorite.databinding.FragmentFavoriteTvShowsBinding
import com.khrisna.favorite.ui.adapter.FavoritePagedAdapter
import com.khrisna.filmdb.viewmodel.ViewModelFactory


class FavoriteTVShowsFragment : Fragment() {

    private lateinit var model: com.khrisna.favorite.viewmodel.FavoritesViewModel
    private lateinit var favoriteListAdapter: FavoritePagedAdapter
    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _binding as FragmentFavoriteTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)

            model.getFavoritesAsPaged(false).observe(viewLifecycleOwner, Observer { data ->
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

        binding.rvFavoriteTvshows.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = favoriteListAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): com.khrisna.favorite.viewmodel.FavoritesViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))
        return ViewModelProviders.of(activity, factory)
            .get(com.khrisna.favorite.viewmodel.FavoritesViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(): FavoriteTVShowsFragment {
            return FavoriteTVShowsFragment()
        }
    }
}
