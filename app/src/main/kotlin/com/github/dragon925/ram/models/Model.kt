package com.github.dragon925.ram.models

import kotlinx.datetime.LocalDateTime

sealed interface Model {
    val id: Int
    val name: String
    val created: LocalDateTime
}