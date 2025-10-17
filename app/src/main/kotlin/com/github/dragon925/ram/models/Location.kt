package com.github.dragon925.ram.models

import kotlinx.datetime.LocalDateTime

data class Location(
    override val id: Int,
    override val name: String,
    val type: String,
    val dimensions: String,
    val residents: List<Int>,
    override val created: LocalDateTime
) : Model
