package com.loveuba.starwarsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.loveuba.starwarsapplication.data.models.Character
import com.loveuba.starwarsapplication.databinding.LayoutSearchItemBinding
import kotlinx.android.synthetic.main.layout_search_item.view.*

class SearchAdapter : ListAdapter<Character, SearchAdapter.SearchViewHolder>(
    object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
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

        fun bind(item: Character) {
            item.run {
                containerView.characterNameTv.text = item.name
            }
        }
    }

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    public override fun getItem(position: Int): Character = super.getItem(position)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bnd = LayoutSearchItemBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(bnd.root, onItemClickListener)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}