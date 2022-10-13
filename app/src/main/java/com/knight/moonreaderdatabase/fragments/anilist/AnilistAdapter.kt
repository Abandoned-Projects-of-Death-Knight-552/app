package com.knight.moonreaderdatabase.fragments.anilist

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
import com.knight.moonreaderdatabase.databinding.AnilistItemBinding
import com.knight.moonreaderdatabase.dataclass.Media


class AnilistAdapter(context: Context) : RecyclerView.Adapter<AnilistAdapter.ViewHolder>() {
    private var values = emptyList<Media>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            AnilistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val posId = position + 1
        holder.idView.text = item.description
        holder.contentView.text = item.title!!.userPreferred
        Glide.with(holder.itemView.context)
            .load(item.coverImage?.large)
            .skipMemoryCache(true)
            .error(R.drawable.ic_image_error)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            var imageurl: String = ""

                if (item.coverImage?.extraLarge != null) {
                imageurl = item.coverImage!!.extraLarge!!
            } else {
                    imageurl = item.coverImage!!.large!!
            }
            val book = LightNovel(0, item.title!!.userPreferred, item.description, "",
                imageurl , "")

            val action = AnilistFragmentDirections.actionAnilistFragmentToAddFragment()
            action.book = book
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: AnilistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.anilistSynopsis
        val contentView: TextView = binding.content
        val image: ImageView = binding.anilistCover

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(in_data: List<Media>) {
        this.values = in_data
        notifyDataSetChanged()
        println(values)
    }

}