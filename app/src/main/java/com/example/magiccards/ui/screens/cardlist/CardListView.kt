package com.example.magiccards.ui.screens.cardlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.magiccards.ui.MagicApp
import com.example.magiccards.R
import com.example.magiccards.data.entities.Card
import com.example.magiccards.ui.common.MyTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardListView(
    onItemClick: (String) -> Unit,
    onUpClick: () -> Unit,
    viewModel: CardListViewModel = koinViewModel()
) {
    var cardList by rememberSaveable {
        mutableStateOf(emptyList<Card>())
    }

    LaunchedEffect(key1 = Unit) {
        cardList = viewModel.getList()
    }
    Scaffold(
        topBar = {
            MyTopAppBar(title = stringResource(id = R.string.app_name)) {
                onUpClick()
            }
        },
    ) { paddingValues ->
        if (cardList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier.padding(paddingValues)) {
                CardsList(cardList, onItemClick)
            }
        }

    }
}


@Preview
@Composable
fun CardListPrev() {
    MagicApp {
        CardListView({}, {})
    }
}

