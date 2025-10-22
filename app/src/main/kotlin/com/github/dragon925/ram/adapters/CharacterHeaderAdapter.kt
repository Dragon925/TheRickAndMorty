package com.github.dragon925.ram.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dragon925.ram.R
import com.github.dragon925.ram.adapters.CharacterHeaderAdapter.CharacterHeaderViewHolder
import com.github.dragon925.ram.databinding.ItemCharacterHeaderBinding
import com.github.dragon925.ram.items.CharacterHeaderItem
import com.github.dragon925.ram.items.LocationItemSmall
import com.github.dragon925.ram.models.CharacterStatus.ALIVE
import com.github.dragon925.ram.models.CharacterStatus.DEAD
import com.github.dragon925.ram.models.CharacterStatus.UNKNOWN
import com.github.dragon925.ram.models.Gender
import com.github.dragon925.ram.utils.getThemedColor
import com.github.dragon925.ram.utils.withBindingContext
import androidx.appcompat.R as androidR
import com.google.android.material.R as materialR

class CharacterHeaderAdapter(
    private val onLocationClick: (LocationItemSmall) -> Unit = {}
) : RecyclerView.Adapter<CharacterHeaderViewHolder>() {

    private enum class DataState {
        ADDED, CHANGED, REMOVED, IDLE
    }

    var header: CharacterHeaderItem? = null
        set(value) {
            val state = when {
                field == null && value != null -> DataState.ADDED
                value != null -> DataState.CHANGED
                field != null -> DataState.REMOVED
                else -> DataState.IDLE
            }
            field = value
            when(state) {
                DataState.ADDED -> notifyItemInserted(0)
                DataState.CHANGED -> notifyItemChanged(0)
                DataState.REMOVED -> notifyItemRemoved(0)
                DataState.IDLE -> Unit
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterHeaderViewHolder (
        ItemCharacterHeaderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(
        holder: CharacterHeaderViewHolder,
        position: Int
    ) {
        val item = header ?: return
        holder.bind(item)
    }

    override fun getItemCount(): Int = if (header != null) 1 else 0

    inner class CharacterHeaderViewHolder(
        private val binding: ItemCharacterHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterHeaderItem) {
            with(binding) {
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

                    val gender = getString(when (item.gender) {
                        Gender.MALE -> R.string.character_gender_male
                        Gender.FEMALE -> R.string.character_gender_female
                        Gender.GENDERLESS -> R.string.character_gender_genderless
                        Gender.UNKNOWN -> R.string.character_gender_unknown
                    } )

                    vStatus.backgroundTintList = ColorStateList.valueOf(statusColorInt)

                    tvStatus.setText(statusId)
                    tvGender.text = gender
                }
                tvSpecies.text = item.species
                tvType.text = item.type

                originLocation.root.setOnClickListener { onLocationClick(item.originLocation) }
                originLocation.tvName.text = item.originLocation.name
                originLocation.tvDimension.text = item.originLocation.dimension

                lastKnownLocation.root.setOnClickListener { onLocationClick(item.lastLocation) }
                lastKnownLocation.tvName.text = item.lastLocation.name
                lastKnownLocation.tvDimension.text = item.lastLocation.dimension
            }
        }
    }
}