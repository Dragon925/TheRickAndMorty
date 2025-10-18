package com.github.dragon925.ram.mappers

import com.github.dragon925.ram.dto.CharacterDto
import com.github.dragon925.ram.models.Character
import com.github.dragon925.ram.models.CharacterStatus
import com.github.dragon925.ram.models.Gender

fun CharacterDto.toModel() = Character(
    id = id,
    name = name,
    status = status.toCharacterStatus(),
    species = species,
    type = type,
    gender = gender.toGender(),
    origin = origin.toModel(),
    location = location.toModel(),
    image = image,
    episode = episode.map(::getIdFromUrl),
    created = parseDateTime(created)
)

private fun String.toCharacterStatus(): CharacterStatus = this.uppercase().let { status ->
    CharacterStatus.entries.firstOrNull { it.name == status } ?: CharacterStatus.UNKNOWN
}

private fun String.toGender(): Gender = this.uppercase().let { gender ->
    Gender.entries.firstOrNull { it.name == gender } ?: Gender.UNKNOWN
}

private fun CharacterDto.Location.toModel() = Character.Location(
    id = getIdFromUrl(url),
    name = name
)