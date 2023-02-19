package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.ui.common.MyAsyncImage
import com.example.magiccards.ui.theme.listItemImageSize
import com.example.magiccards.ui.theme.lowPadding
import com.example.magiccards.ui.theme.mediumPadding
import com.example.magiccards.ui.theme.minPadding

@Composable
fun CardItem(localMagicCardData: MagicCardEntity, onItemClick: (id: String) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .clickable {
                onItemClick(localMagicCardData.id)
            }
            .padding(lowPadding)
            .fillMaxWidth(),
        shape = RoundedCornerShape(lowPadding),
        elevation = mediumPadding
    ) {
        Row(
            modifier = Modifier
                .padding(minPadding),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            MyAsyncImage(
                url = localMagicCardData.imageUrl,
                imageSize = listItemImageSize,
                context = context
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = minPadding),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = localMagicCardData.name, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.padding(top = minPadding))
                Text(
                    text = localMagicCardData.text,
                    style = MaterialTheme.typography.body2,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}