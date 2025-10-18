package com.github.dragon925.ram.dto

data class EpisodeDto(
    override val id: Int,
    override val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    override val url: String,
    override val created: String
) : ModelDto