package com.knight.moonreaderdatabase.fragments.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.databinding.FragmentUpdateBinding
import com.knight.moonreaderdatabase.utils.MainVMFactory
import com.knight.moonreaderdatabase.utils.RFRepo
import com.knight.moonreaderdatabase.utils.RFViewM
import com.knight.moonreaderdatabase.utils.Validator
import java.io.IOException


class UpdateFragment : Fragment() {
    private val args: UpdateFragmentArgs by navArgs()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel
    private lateinit var viewModel: RFViewM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val view = binding.root
        val bookid = args.bookID

        val repo = RFRepo()
        val viewMfactory = MainVMFactory(repo)
        viewModel = ViewModelProvider(this, viewMfactory)[RFViewM::class.java]



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

        binding.updateFetch.setOnClickListener {
            try {
                viewModel.getBook(binding.updateTitle.text.toString())
                val bob = viewModel.lolRes.value

                viewModel.lolRes.observe(viewLifecycleOwner) { resp ->
                    binding.updateSynopsis.setText(resp.Media!!.description.toString())
                    if (resp.Media!!.coverImage!!.extraLarge != null) {
                    binding.updateCoverRemote.setText(resp.Media!!.coverImage!!.extraLarge.toString())}
                    else {
                        binding.updateCoverRemote.setText(resp.Media!!.coverImage!!.large.toString())
                }

                    Glide.with(requireContext())
                        .load(resp.Media!!.coverImage!!.large)
                        .error(R.drawable.ic_image_error)
                        .into(binding.prevContent)

                }
            } catch (_: IOException) {

        }


        }


        binding.BTNUpdate.setOnClickListener {
            updateEntry(bookid)
        }



        return view
    }

    private fun updateEntry(bookid: Int) {
        val title = binding.updateTitle.text.toString()
        val download = binding.updateDownload.text.toString()
        val synopsis = binding.updateSynopsis.text.toString()
        val coverUrl = binding.updateCoverRemote.text.toString()
        val filepath = binding.updateFilePath.text.toString()

        when (Validator.validateEntry(title, download, synopsis, coverUrl, filepath)) {
            110 -> Toast.makeText(context, "Title field is Empty", Toast.LENGTH_SHORT).show()
            120 -> Toast.makeText(context, "Download field is Empty", Toast.LENGTH_SHORT).show()
            121 -> Toast.makeText(context, "Download is not valid url", Toast.LENGTH_SHORT).show()
            130 -> Toast.makeText(context, "Cover is not valid url", Toast.LENGTH_SHORT).show()
            200 -> {
                bookViewModel.updateBook(
                    LightNovel(
                        bookid,
                        title,
                        synopsis,
                        download,
                        coverUrl,
                        filepath
                    )
                )
                val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(bookid)
                findNavController().navigate(action)
            }
        }

//        val action = UpdateFragmentDirections.
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}