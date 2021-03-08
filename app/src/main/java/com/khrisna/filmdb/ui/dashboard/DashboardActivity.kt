package com.khrisna.filmdb.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.ActivityHomeBinding
import com.khrisna.filmdb.ui.home.HomeFragment
import com.khrisna.filmdb.ui.search.SearchFragment


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFragment(HomeFragment.newInstance())
        }

        binding.fab.setOnClickListener {
            showFragment(SearchFragment.newInstance())
        }
    }

    private fun showFragment(fragmentInstance: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                fragmentInstance
            )
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_home -> {
                showFragment(HomeFragment.newInstance())
                Toast.makeText(this, "Hey!", Toast.LENGTH_LONG).show()
            }
            R.id.app_bar_favorite -> {
                showFragment(
                    Class.forName("com.khrisna.favorite.ui.FavoritesFragment")
                        .newInstance() as Fragment
                )
            }
//            R.id.app_bar_translation -> Toast.makeText(
//                this, "Setting menu item is clicked!",
//                Toast.LENGTH_SHORT
//            ).show()
        }

        return true
    }
}