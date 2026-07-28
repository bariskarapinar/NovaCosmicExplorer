package com.cosmic.nova.data.model

data class Planet(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val imageUrl: String,
    val temperature: String,
    val mass: String,
    val distance: String,
    val colorHex: Long
)
