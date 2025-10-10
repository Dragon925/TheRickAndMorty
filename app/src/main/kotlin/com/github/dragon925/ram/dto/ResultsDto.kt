package com.github.dragon925.ram.dto

data class ResultsDto<out T: ModelDto>(
    val info: InfoDto,
    val results: List<T>
)