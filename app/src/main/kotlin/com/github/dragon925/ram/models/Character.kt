package com.github.dragon925.ram.models

import kotlinx.datetime.LocalDateTime

data class Character(
    override val id: Int,
    override val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<Int>,
    override val created: LocalDateTime
) : Model {
    data class Location(
        val id: Int,
        val name: String
    )
}
