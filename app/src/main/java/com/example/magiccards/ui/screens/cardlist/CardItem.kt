package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.magiccards.R
import com.example.magiccards.data.entities.Card
import com.example.magiccards.ui.common.MyAsyncImage
import com.example.magiccards.ui.theme.listItemImageSize
import com.example.magiccards.ui.theme.lowPadding
import com.example.magiccards.ui.theme.minPadding

// TODO: Implement original data from API
@Composable
fun CardItem(cardData: Card, onItemClick: (id: String) -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clickable {
                onItemClick(cardData.name)
            }
            .padding(lowPadding),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        val cardImage = cardData.imageUrl
            ?: stringResource(id = R.string.placeHolderLink)
        MyAsyncImage(url = cardImage, imageSize = listItemImageSize, context = context)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = minPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = cardData.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.padding(top = minPadding))
            Text(
                text = cardData.text,
                style = MaterialTheme.typography.body2,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
