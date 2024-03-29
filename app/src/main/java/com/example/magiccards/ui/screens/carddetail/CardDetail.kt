package com.example.magiccards.ui.screens.carddetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.magiccards.R
import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.ui.theme.mediumPadding
import com.example.magiccards.ui.theme.minPadding

@Composable
fun CardDetail(localMagicCard: MagicCardEntity) {
    Column(
        modifier = Modifier
            .padding(minPadding)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.description_text),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = localMagicCard.text,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(R.string.mana_cost_text).plus(" ${localMagicCard.manaCost}"),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = stringResource(R.string.rarity_text).plus(" ${localMagicCard.rarity}"),
            style = MaterialTheme.typography.caption
        )
    }
}