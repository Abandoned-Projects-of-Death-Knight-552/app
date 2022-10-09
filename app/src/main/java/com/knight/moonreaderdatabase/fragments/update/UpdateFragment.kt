package com.knight.moonreaderdatabase.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private val args: UpdateFragmentArgs by navArgs()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val view = binding.root
        val bookid = args.bookID

//        setting database
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        bookViewModel.fetchLiveBook(bookid).observe(viewLifecycleOwner) {
            res ->
            binding.updateTitle.setText(res.title)
            binding.updateDownload.setText(res.download)
            binding.updateSynopsis.setText(res.synopsis)
            binding.updateCoverRemote.setText(res.coverRemote)
            binding.updateFilePath.setText(res.coverLocal)

        }


        binding.BTNUpdate.setOnClickListener {
            updateEntry(bookid)
        }

//


        return view
    }

    private fun updateEntry(bookid: Int) {
        val title = binding.updateTitle.text.toString()
        val download = binding.updateDownload.text.toString()
        val synopsis = binding.updateSynopsis.text.toString()
        val coverUrl = binding.updateCoverRemote.text.toString()
        val filepath = binding.updateFilePath.text.toString()
        bookViewModel.updateBook(LightNovel(bookid, title, synopsis, download, coverUrl, filepath ))

//        val action = UpdateFragmentDirections.
        findNavController().navigate(R.id.action_updateFragment_to_bookFragment)
    }

}