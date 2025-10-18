package com.github.dragon925.ram.mappers

import com.github.dragon925.ram.dto.LocationDto
import com.github.dragon925.ram.models.Location

fun LocationDto.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimensions = dimensions,
    residents = residents.map(::getIdFromUrl),
    created = parseDateTime(created)
)