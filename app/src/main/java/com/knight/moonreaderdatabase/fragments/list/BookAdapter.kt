package com.knight.moonreaderdatabase.fragments.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.knight.moonreaderdatabase.R

import com.knight.moonreaderdatabase.placeholder.PlaceholderContent.PlaceholderItem
import com.knight.moonreaderdatabase.databinding.SingleBookBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class BookAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

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
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_bookFragment_to_addFragment)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: SingleBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}