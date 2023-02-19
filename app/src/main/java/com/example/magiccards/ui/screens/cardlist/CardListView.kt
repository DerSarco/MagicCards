package com.example.magiccards.ui.screens.cardlist

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.magiccards.ui.MagicApp
import com.example.magiccards.R
import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.ui.common.MyErrorView
import com.example.magiccards.ui.common.MyTopAppBar
import com.example.magiccards.ui.viewmodel.CardListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardListView(
    onItemClick: (String) -> Unit,
    onUpClick: () -> Unit,
    viewModel: CardListViewModel = koinViewModel()
) {
    val context = LocalContext.current
    var localMagicCardList by rememberSaveable {
        mutableStateOf(emptyList<MagicCardEntity>())
    }
    var error by rememberSaveable {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = Unit) {
        when (val response = viewModel.getList()) {
            is ApiResponse.Success -> localMagicCardList = response.data
            is ApiResponse.Error -> {
                error = true
                Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        topBar = {
            MyTopAppBar(title = stringResource(id = R.string.app_name)) {
                onUpClick()
            }
        },
    ) { paddingValues ->
        if (error) {
            MyErrorView()
        }
        if (localMagicCardList.isEmpty() && !error) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier.padding(paddingValues)) {
                CardsList(localMagicCardList, onItemClick)
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