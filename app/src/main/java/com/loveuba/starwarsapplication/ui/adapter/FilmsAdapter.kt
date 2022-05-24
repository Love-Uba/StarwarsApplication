package com.loveuba.starwarsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.databinding.LayoutFilmItemBinding
import com.loveuba.starwarsapplication.databinding.LayoutSearchItemBinding
import kotlinx.android.synthetic.main.layout_film_item.view.*
import kotlinx.android.synthetic.main.layout_search_item.view.*

class FilmsAdapter : ListAdapter<FilmData, FilmsAdapter.FilmViewHolder>(
    object : DiffUtil.ItemCallback<FilmData>() {
        override fun areItemsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: FilmData, newItem: FilmData): Boolean =
            oldItem == newItem
    }
) {
    class FilmViewHolder(
        val containerView: View,
        onItemClickListener: ((position: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(
        containerView
    ) {
        init {
            containerView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(item: FilmData) {
            item.run {
                containerView.filmTitleTv.text = item.title
                containerView.FilmDescriptionTv.text = item.opening_crawl
            }
        }
    }

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    public override fun getItem(position: Int): FilmData = super.getItem(position)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bnd = LayoutFilmItemBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(bnd.root, onItemClickListener)

    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}