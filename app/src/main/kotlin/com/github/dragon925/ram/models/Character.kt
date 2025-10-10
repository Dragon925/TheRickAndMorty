package com.github.dragon925.ram.models

import kotlinx.datetime.LocalDateTime

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val type: String,
    // ...
    val created: LocalDateTime
)
