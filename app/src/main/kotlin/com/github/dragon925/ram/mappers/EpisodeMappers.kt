package com.github.dragon925.ram.mappers

import com.github.dragon925.ram.dto.EpisodeDto
import com.github.dragon925.ram.models.Episode

fun EpisodeDto.toModel() = Episode(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters.map(::getIdFromUrl),
    created = parseDateTime(created)
)