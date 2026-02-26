package com.example.tipper

data class RSSitem(
    val title: String,
    val text: String,
    val type: RSSType,
    val media: Int? = null
)

enum class RSSType {
    TEXT,
    VIDEO,
    IMAGE
}