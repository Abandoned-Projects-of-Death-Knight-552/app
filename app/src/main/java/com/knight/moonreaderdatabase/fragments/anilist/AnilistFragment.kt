package com.knight.moonreaderdatabase.fragments.anilist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.utils.MainVMFactory
import com.knight.moonreaderdatabase.utils.RFRepo
import com.knight.moonreaderdatabase.utils.RFViewM

class AnilistFragment : Fragment() {

    private var columnCount = 1
    private lateinit var viewModel: RFViewM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.anilist_list, container, false)

        val repo = RFRepo()
        val viewMfactory = MainVMFactory(repo)
        viewModel = ViewModelProvider(this, viewMfactory)[RFViewM::class.java]

        val textSearch: EditText = view.findViewById(R.id.anilist_search)
        val search: Button = view.findViewById(R.id.AnilistsearchButton)
        var recyclerView: RecyclerView = view.findViewById(R.id.list)


        val textView: TextView = view.findViewById(R.id.anilist_loading)
        textView.visibility = View.GONE

        search.setOnClickListener {
            textView.visibility = View.VISIBLE
            viewModel.getSearchResult(textSearch.text.toString())
            recyclerView.visibility = View.GONE
        }

        val ADAPTER = AnilistAdapter(requireContext())

        viewModel.anilist_list.observe(viewLifecycleOwner) {
            val arrayReso = it.page.media
            ADAPTER.setData(arrayReso)
            textView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }


        // Set the adapter

            with(recyclerView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ADAPTER
            }


        return view
    }

}