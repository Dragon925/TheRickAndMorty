package com.github.dragon925.ram.dto

data class LocationDto(
    override val id: Int,
    override val name: String,
    val type: String,
    val dimensions: String,
    val residents: List<String>,
    override val url: String,
    override val created: String
) : ModelDto
