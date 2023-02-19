package com.example.magiccards.util

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse

fun getFakeCardList(): ApiResponse<List<MagicCardEntity>> {
    val list = (1..10).map {
        MagicCardEntity(
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

fun getFakeCard(): ApiResponse<MagicCardEntity> = ApiResponse.Success(
    MagicCardEntity(
        id = "id",
        artist = "artist",
        name = "name",
        imageUrl = null,
        manaCost = "mana",
        rarity = "rare",
        text = "description"
    )
)

fun getFakeErrorCard(): ApiResponse<MagicCardEntity> {
    return ApiResponse.Error(400, "Generic error")
}

fun getFakeErrorCardList(): ApiResponse<List<MagicCardEntity>> {
    return ApiResponse.Error(400, "Generic error")
}