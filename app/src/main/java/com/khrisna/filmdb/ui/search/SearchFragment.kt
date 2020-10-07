package com.khrisna.filmdb.ui.search

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
import com.khrisna.filmdb.ui.adapter.search.SearchAdapter
import com.khrisna.filmdb.viewmodel.SearchViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var model: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var progressBar: ProgressBar
    private var query: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->
            model = obtainViewModel(activity as AppCompatActivity)

            btn_search.setOnClickListener {
                query = edt_query.text.toString()

                model.getSearches(query).observe(viewLifecycleOwner, Observer { data ->

                    data.let {

                        searchAdapter.submitList(data.searches)
                        searchAdapter.notifyDataSetChanged()

                        progressBar.visibility = View.INVISIBLE
                    }
                })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter(context as AppCompatActivity)

        progressBar = view.findViewById(R.id.progressBar)

        rv_search.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = searchAdapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): SearchViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))
        return ViewModelProviders.of(activity, factory).get(SearchViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
