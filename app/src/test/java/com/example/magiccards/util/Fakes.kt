package com.example.magiccards.util

import com.example.magiccards.data.entities.LocalMagicCard
import com.example.magiccards.data.network.ApiResponse

fun getFakeCardList(): ApiResponse<List<LocalMagicCard>>{
    val list =(1..10).map {
        LocalMagicCard(
            id = it.toString(),
            artist = "artist",
            name = "name",
            imageUrl = null,
            manaCost = "mana",
            rarity = "rare",
            text = "description"
        )
    }
   return ApiResponse.Success(list)
}

fun getFakeErrorCardList(): ApiResponse<List<LocalMagicCard>> {
    return ApiResponse.Error(400, "Generic error")
}