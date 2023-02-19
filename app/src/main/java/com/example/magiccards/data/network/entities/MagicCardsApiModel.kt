package com.example.magiccards.data.network.entities

import com.google.gson.annotations.SerializedName

data class MagicCardsApiModel(
    @SerializedName("cards")
    val cards: List<MagicCardApiModel>
)
