package com.example.magiccards.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.magiccards.R
import com.example.magiccards.ui.theme.minPadding

@Composable
fun MyTopAppBar(title: String, onUpClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
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
}