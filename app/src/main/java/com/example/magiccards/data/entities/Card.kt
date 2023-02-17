package com.example.magiccards.data.entities

data class Card(
    val id: String,
    val artist: String,
    val name: String,
    val imageUrl: String? = null,
    val manaCost: String,
    val rarity: String,
    val text: String
)
