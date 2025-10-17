package com.github.dragon925.ram.mappers

fun getIdFromUrl(url: String): Int = url
    .trimEnd('/')
    .substringAfterLast("/")
    .toInt()