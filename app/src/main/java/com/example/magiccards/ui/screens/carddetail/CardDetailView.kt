package com.example.magiccards.ui.screens.carddetail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.magiccards.R
import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.data.network.ApiResponse
import com.example.magiccards.ui.common.MyAsyncImage
import com.example.magiccards.ui.common.MyCircularProgress
import com.example.magiccards.ui.common.MyErrorView
import com.example.magiccards.ui.common.MyTopAppBar
import com.example.magiccards.ui.theme.defaultFontSize
import com.example.magiccards.ui.theme.detailViewImageSize
import com.example.magiccards.ui.theme.mediumPadding
import com.example.magiccards.ui.viewmodel.CardListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardDetailView(
    id: String,
    viewModel: CardListViewModel = koinViewModel(),
    onUpClick: () -> Unit
) {
    val context = LocalContext.current
    var localMagicCard by rememberSaveable {
        mutableStateOf<MagicCardEntity?>(null)
    }
    var error by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        when (val response = viewModel.getCard(id)) {
            is ApiResponse.Success -> localMagicCard = response.data
            is ApiResponse.Error -> {
                error = true
                Toast.makeText(context, "${response.code}: ${response.message}", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                error = true
                Toast.makeText(
                    context,
                    context.getString(R.string.generic_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = localMagicCard?.name ?: stringResource(R.string.loading_message),
                onUpClick = onUpClick
            )
        }
    ) { paddingValues ->
        if (error) {
            MyErrorView()
        }
        if (localMagicCard == null && !error) {
            MyCircularProgress()
        } else {
            Card(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(mediumPadding),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyAsyncImage(localMagicCard!!.imageUrl, detailViewImageSize, context)
                    Text(
                        text = "Artist: ${localMagicCard!!.artist}",
                        style = MaterialTheme.typography.caption,
                        fontSize = defaultFontSize
                    )
                    CardDetail(localMagicCard!!)
                }
            }
        }
    }
}
