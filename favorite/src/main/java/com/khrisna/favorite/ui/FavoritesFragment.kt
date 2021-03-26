package com.khrisna.favorite.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.khrisna.favorite.databinding.FragmentFavoritesBinding
import com.khrisna.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding as FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        binding.viewPager.adapter = ScreenSlidePagerAdapter(context as AppCompatActivity)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }

    inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> FavoriteMoviesFragment.newInstance()
                1 -> FavoriteTVShowsFragment.newInstance()
                else -> FavoriteMoviesFragment.newInstance()
            }
    }

    companion object {

        private const val NUM_PAGES = 2
        private val tabs = arrayOf(
            "Movie",
            "TVShow"
        )

        @JvmStatic
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }
}
