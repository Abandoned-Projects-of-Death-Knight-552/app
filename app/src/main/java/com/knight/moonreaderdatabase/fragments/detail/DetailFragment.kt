@file:Suppress("DEPRECATION")

package com.knight.moonreaderdatabase.fragments.detail

import android.app.AlertDialog
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
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookLN: LightNovel

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
            bookLN = book
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

            R.id.detail_menu_delete_book -> {
                deleteBook(bookLN)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook(Ln: LightNovel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Delete") {_,_ ->
            bookViewModel.deleteBook(Ln)
            findNavController().navigate(R.id.action_detailFragment_to_bookFragment)

        }
        builder.setNegativeButton("Cancel") {_,_ ->}
        builder.setTitle("Delete Entry?")
        builder.setMessage("Are you sure you want to delete ${Ln.title}")

        builder.create().show()


    }
}