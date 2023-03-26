@file:Suppress("DEPRECATION")

package com.knight.moonreaderdatabase.fragments.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel

class BookFragment : Fragment() , SearchView.OnQueryTextListener {

    private var columnCount = 1
    private lateinit var bookViewModel: BookViewModel
    private lateinit var myAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        val view = inflater.inflate(R.layout.main_book_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.mainList)
        val fab_add_button: FloatingActionButton = view.findViewById(R.id.FAB_add_new)
        val fab_anilist: FloatingActionButton = view.findViewById(R.id.FAB_anilist)


        setHasOptionsMenu(true)
        //setting database
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        myAdapter = BookAdapter(requireContext())

        bookViewModel.allBooks.observe(viewLifecycleOwner) {
            myAdapter.setBooks(it)
        }

        with(recyclerView) {
            adapter = myAdapter
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

        }

        fab_add_button.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_addFragment)
        }

        fab_anilist.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_anilistFragment)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)

        val search = menu.findItem(R.id.list_menu_search)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_menu_setting -> findNavController().navigate(R.id.action_bookFragment_to_settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null ) searchDB(newText)
        return true
    }

    private fun searchDB(string: String) {
        myAdapter.filterList(string)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}