package com.github.dragon925.ram.items

import com.github.dragon925.ram.models.CharacterStatus
import com.github.dragon925.ram.models.Gender

data class CharacterHeaderItem(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: Gender,
    val type: String,
    val originLocation: LocationItemSmall,
    val lastLocation: LocationItemSmall
)
