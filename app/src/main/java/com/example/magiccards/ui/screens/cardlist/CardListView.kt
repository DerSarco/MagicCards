package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.magiccards.MagicApp
import com.example.magiccards.R
import com.example.magiccards.data.entities.ColumnTest
import com.example.magiccards.data.entities.getColumnTestData
import com.example.magiccards.ui.theme.lowPadding
import com.example.magiccards.ui.theme.minPadding

@Composable
fun CardListView(onUpClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onUpClick()
                            }
                            .padding(start = minPadding),
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.navDescriptionText
                        )
                    )
                }
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            CardsList()
        }
    }
}

@Composable
fun CardsList() {
    val list = getColumnTestData()
    LazyColumn {
        items(items = list) { cardData ->
            CardItem(cardData)
        }
    }
}

// TODO: Implement original data from API
@Composable
fun CardItem(cardData: ColumnTest) {
    Row(
        modifier = Modifier.padding(lowPadding),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "generic image"
        )
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
                text = cardData.description,
                style = MaterialTheme.typography.body2,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
fun CardListPrev() {
    MagicApp {
        CardListView {}
    }
}

