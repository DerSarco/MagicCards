package com.example.magiccards.util

import com.example.magiccards.data.network.entities.MagicCardApiModel
import com.example.magiccards.data.network.entities.MagicCardsApiModel

fun getFakeCardListService(): MagicCardsApiModel {
    return MagicCardsApiModel(
        cards = (1..10).map {
            MagicCardApiModel(
                "",
                2.0,
                emptyList(),
                emptyList(),
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                "",
                "",
                "",
                emptyList(),
                emptyList(),
            )
        }
    )
}

fun getFakeCardService() =
    MagicCardApiModel(
        "",
        2.0,
        emptyList(),
        emptyList(),
        "",
        "1",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        emptyList(),
        "",
        "",
        "",
        emptyList(),
        emptyList(),
        "",
        "",
        "",
        emptyList(),
        emptyList(),
    )
