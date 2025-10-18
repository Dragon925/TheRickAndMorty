package com.github.dragon925.ram.models

import kotlinx.datetime.LocalDateTime

data class Episode(
    override val id: Int,
    override val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Int>,
    override val created: LocalDateTime
) : Model
