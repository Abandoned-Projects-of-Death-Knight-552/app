package com.knight.moonreaderdatabase.fragments.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.knight.moonreaderdatabase.R
import com.knight.moonreaderdatabase.database.LightNovel

import com.knight.moonreaderdatabase.placeholder.PlaceholderContent.PlaceholderItem
import com.knight.moonreaderdatabase.databinding.SingleBookBinding

class BookAdapter(context: Context) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

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
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.title
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_bookFragment_to_addFragment)
        }
    }

    override fun getItemCount(): Int = books.size

    inner class ViewHolder(binding: SingleBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setBooks(lightNovels: List<LightNovel>) {
        this.books = lightNovels
        notifyDataSetChanged()
    }

}