@file:Suppress("DEPRECATION")

package com.knight.moonreaderdatabase.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.placeholder.PlaceholderContent

class BookFragment : Fragment() {

    private var columnCount = 1
    private lateinit var bookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_book_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.mainList)
        val fab_add_button: FloatingActionButton = view.findViewById(R.id.FAB_add_new)


        setHasOptionsMenu(true)
        //setting database
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        // Set the adapter
        val ADAPTER = context?.let { BookAdapter(it) }
        bookViewModel.allBooks.observe(viewLifecycleOwner) { books ->
            ADAPTER!!.setBooks(books)
        }
        with(recyclerView) {
            adapter = ADAPTER
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

        }

        fab_add_button.setOnClickListener {
            findNavController().navigate(R.id.action_bookFragment_to_addFragment)
        }

        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_menu_setting -> findNavController().navigate(R.id.action_bookFragment_to_settingsFragment)
        }

        return super.onOptionsItemSelected(item)
    }
}