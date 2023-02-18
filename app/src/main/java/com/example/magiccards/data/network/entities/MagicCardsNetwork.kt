package com.example.magiccards.data.network.entities

import com.google.gson.annotations.SerializedName

data class MagicCardsNetwork(
    @SerializedName("cards")
    val cards: List<MagicCardNetwork>
)