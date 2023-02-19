package com.example.magiccards.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class parcelable because with rememberSaveable in CardDetail view crashed on rotation.
 */
@Parcelize
data class MagicCardEntity(
    val id: String,
    val artist: String,
    val name: String,
    val imageUrl: String? = null,
    val manaCost: String,
    val rarity: String,
    val text: String
) : Parcelable
