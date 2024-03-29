package com.knight.moonreaderdatabase.fragments.list

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.LightNovel

import com.knight.moonreaderdatabase.databinding.SingleBookBinding

class BookAdapter(context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var main_books = emptyList<LightNovel>()

    private var books = emptyList<LightNovel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SingleBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = books[position]
        val posId = position + 1
        holder.idView.text = String.format("%02d", posId)
        holder.contentView.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.coverRemote)
            .error(R.drawable.ic_image_error)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
//            holder.itemView.findNavController().navigate(R.id.action_bookFragment_to_detailFragment)
            val action = BookFragmentDirections.actionBookFragmentToDetailFragment(item.id)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(binding: SingleBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val imageView: ImageView = binding.listCover
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBooks(lightNovels: List<LightNovel>) {
        this.main_books = lightNovels
        this.books = lightNovels
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(string: String) {
        this.books = main_books.filter { it.title!!.contains(string, true)}
        notifyDataSetChanged()
    }
}