package com.loveuba.starwarsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.databinding.LayoutSearchItemBinding
import kotlinx.android.synthetic.main.layout_search_item.view.*

class SearchAdapter : ListAdapter<CharacterData, SearchAdapter.SearchViewHolder>(
    object : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean =
            oldItem == newItem
    }
) {
    class SearchViewHolder(
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

        fun bind(item: CharacterData) {
            item.run {
                containerView.characterNameTv.text = item.name
            }
        }
    }

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    public override fun getItem(position: Int): CharacterData = super.getItem(position)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bnd = LayoutSearchItemBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(bnd.root, onItemClickListener)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}