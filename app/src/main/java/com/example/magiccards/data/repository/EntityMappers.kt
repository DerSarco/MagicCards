package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.entities.MagicCardApiModel
import com.example.magiccards.data.network.entities.MagicCardsApiModel


fun MagicCardApiModel.toSingleCardEntity() = MagicCardEntity(
    id = this.id,
    artist = this.artist,
    name = this.name,
    imageUrl = this.imageUrl?.toHttps(),
    manaCost = this.manaCost,
    rarity = this.rarity,
    text = this.text
)

fun MagicCardsApiModel.toCard() =
    this.cards.map { cardNetwork ->
        MagicCardEntity(
            id = cardNetwork.id,
            artist = cardNetwork.artist,
            name = cardNetwork.name,
            imageUrl = cardNetwork.imageUrl?.toHttps(),
            manaCost = cardNetwork.manaCost,
            rarity = cardNetwork.rarity,
            text = cardNetwork.text
        )
    }

private fun String.toHttps(): String =
    this.replace("http", "https")