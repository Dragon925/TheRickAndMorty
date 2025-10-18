package com.github.dragon925.ram.dto

data class CharacterDto(
    override val id: Int,
    override val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    override val url: String,
    override val created: String
) : ModelDto {
    data class Location(
        val name: String,
        val url: String
    )
}
