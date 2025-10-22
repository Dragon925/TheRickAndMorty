package com.github.dragon925.ram.items

import com.github.dragon925.ram.models.Character
import com.github.dragon925.ram.models.CharacterStatus
import com.github.dragon925.ram.models.Gender

data class CharacterItem(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: Gender,
    val lastLocation: Character.Location
)
