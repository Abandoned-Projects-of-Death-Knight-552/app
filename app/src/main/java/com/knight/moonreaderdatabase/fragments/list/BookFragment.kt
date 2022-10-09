package com.knight.moonreaderdatabase.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class BookFragment : Fragment() {

    private var columnCount = 1
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_book_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.mainList)
        val fab_add_button: FloatingActionButton = view.findViewById(R.id.FAB_add_new)


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




//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//                adapter = badapter
//
//            }
//        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            BookFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}