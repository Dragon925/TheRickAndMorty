package com.github.dragon925.ram.dto

sealed interface ModelDto {
    val id: Int
    val name: String
    val url: String
    val created: String
}