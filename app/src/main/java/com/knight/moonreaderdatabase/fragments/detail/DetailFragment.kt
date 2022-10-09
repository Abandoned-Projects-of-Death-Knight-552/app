@file:Suppress("DEPRECATION")

package com.knight.moonreaderdatabase.fragments.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val view = binding.root
        val bookid = args.bookID

        setHasOptionsMenu(true)
//        setting database
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        //setting view
        bookViewModel.fetchLiveBook(bookid).observe(viewLifecycleOwner) { book ->
            binding.detailTitle.text = book.title

            if (book.synopsis != null) {
                binding.detailSynopsis.text = book.synopsis
            }

            binding.detailButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.download))
                try {
                    startActivity(intent)

                } catch (_: ActivityNotFoundException) {

                }

            }
        }


        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.detail_menu_update_book -> {
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.bookID)
                findNavController().navigate(action)
            }

            R.id.detail_menu_delete_book -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}