package com.github.dragon925.ram.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dragon925.ram.R
import com.github.dragon925.ram.databinding.ItemCharacterBinding
import com.github.dragon925.ram.items.CharacterItem
import com.github.dragon925.ram.models.CharacterStatus.ALIVE
import com.github.dragon925.ram.models.CharacterStatus.DEAD
import com.github.dragon925.ram.models.CharacterStatus.UNKNOWN
import com.github.dragon925.ram.models.Gender
import com.github.dragon925.ram.utils.getThemedColor
import com.github.dragon925.ram.utils.withBindingContext
import androidx.appcompat.R as androidR
import com.google.android.material.R as materialR

class CharacterListAdapter(
    private val onItemClick: (CharacterItem) -> Unit = {}
) : ListAdapter<CharacterItem, CharacterListAdapter.CharacterViewHolder>(
    ItemDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterItem) {
            with(binding) {
                ivAvatar.setImageResource(R.drawable.character_image)
                // TODO load avatar
                tvName.text = item.name
                withBindingContext {
                    val (statusId, statusColorInt) = when (item.status) {
                        ALIVE -> R.string.character_status_alive to getThemedColor(
                            materialR.attr.colorTertiary
                        )
                        DEAD -> R.string.character_status_dead to getThemedColor(
                            androidR.attr.colorError
                        )
                        UNKNOWN -> R.string.character_status_unknown to getThemedColor(
                            materialR.attr.colorOutline
                        )
                    }

                    val status = getString(statusId)

                    val gender = getString(when (item.gender) {
                        Gender.MALE -> R.string.character_gender_male
                        Gender.FEMALE -> R.string.character_gender_female
                        Gender.GENDERLESS -> R.string.character_gender_genderless
                        Gender.UNKNOWN -> R.string.character_gender_unknown
                    } )

                    vStatus.backgroundTintList = ColorStateList.valueOf(statusColorInt)

                    tvStatusAndSpecies.text = getString(
                        R.string.character_staus_and_species, status, item.species
                    )
                    tvGender.text = gender
                }
                tvLastLocation.text = item.lastLocation.name
                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(
            oldItem: CharacterItem,
            newItem: CharacterItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CharacterItem,
            newItem: CharacterItem
        ): Boolean = oldItem == newItem
    }
}