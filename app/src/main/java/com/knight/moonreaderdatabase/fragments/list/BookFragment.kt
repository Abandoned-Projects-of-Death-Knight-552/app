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

        // Set the adapter


        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val badapter = context?.let { BookAdapter(it) }
        bookViewModel.allBooks.observe(viewLifecycleOwner) { books ->
            badapter!!.setBooks(books)
        }



        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = badapter

            }
        }
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