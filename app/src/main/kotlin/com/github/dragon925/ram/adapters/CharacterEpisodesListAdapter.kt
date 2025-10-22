package com.github.dragon925.ram.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dragon925.ram.adapters.CharacterEpisodesListAdapter.*
import com.github.dragon925.ram.databinding.ItemEpisodeSmallBinding
import com.github.dragon925.ram.items.EpisodeItemSmall

class CharacterEpisodesListAdapter(
    private val onItemClick: (EpisodeItemSmall) -> Unit = {}
): ListAdapter<EpisodeItemSmall, CharacterEpisodeItemViewHolder>(
    ItemDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterEpisodeItemViewHolder(
        ItemEpisodeSmallBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(
        holder: CharacterEpisodeItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class CharacterEpisodeItemViewHolder(
        private val binding: ItemEpisodeSmallBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EpisodeItemSmall) {
            with(binding) {
                root.setOnClickListener { onItemClick(item) }

                tvName.text = item.name
                tvEpisode.text = item.episode
                tvAirDate.text = item.airDate
            }
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<EpisodeItemSmall>() {
        override fun areItemsTheSame(
            oldItem: EpisodeItemSmall,
            newItem: EpisodeItemSmall
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: EpisodeItemSmall,
            newItem: EpisodeItemSmall
        ): Boolean = oldItem == newItem
    }
}