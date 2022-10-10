package com.knight.moonreaderdatabase.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.BookViewModel
import com.knight.moonreaderdatabase.database.LightNovel
import com.knight.moonreaderdatabase.databinding.FragmentAddBinding
import com.knight.moonreaderdatabase.dataclass.Dataclass
import com.knight.moonreaderdatabase.utils.Validator
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val vbinding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val view =  vbinding.root
//        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        //setting database
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        vbinding.BTNAddNew.setOnClickListener {
            insertNewBook()
        }

        return view
    }

    private fun insertNewBook() {
        val title = vbinding.ETTitle.text.toString()
        val download = vbinding.ETDownload.text.toString()
        val synopsis = vbinding.ETSynopsis.text.toString()
        val coverUrl = vbinding.ETCoverRemote.text.toString()
        val filepath = vbinding.ETFilePath.text.toString()

        when (Validator.validateEntry(title, download, synopsis, coverUrl, filepath)) {
            110 -> Toast.makeText(context, "Title field is Empty", Toast.LENGTH_SHORT).show()
            120 -> Toast.makeText(context, "Download field is Empty", Toast.LENGTH_SHORT).show()
            121 -> Toast.makeText(context, "Download is not valid url", Toast.LENGTH_SHORT).show()
            130 -> Toast.makeText(context, "Cover is not valid url", Toast.LENGTH_SHORT).show()
            200 -> {
                bookViewModel.insertBook(
                    LightNovel(
                        0,
                        title,
                        synopsis,
                        download,
                        coverUrl,
                        filepath
                    )
                )
                findNavController().navigate(R.id.action_addFragment_to_bookFragment)
            }
        }

    }
}