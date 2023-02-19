package com.example.magiccards.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.magiccards.ui.screens.carddetail.CardDetailView
import com.example.magiccards.ui.screens.cardlist.CardListView
import com.example.magiccards.ui.screens.welcome.Welcome

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestinationView) {
        navigation(
            startDestination = "/$startDestinationView",
            route = startDestinationView
        ) {
            composable("/$startDestinationView") {
                Welcome {
                    navController.navigate("/$cardListDestinationView")
                }
            }
            composable("/$cardListDestinationView") {
                CardListView(
                    onItemClick = {
                        navController.navigate("/$cardDetailDestinationView/$it")
                    },
                    onUpClick = {
                        navController.popBackStack()

                    }
                )
            }
            composable("/$cardDetailDestinationView/{id}") {
                val id = it.arguments?.getString("id")
                requireNotNull(id)
                CardDetailView(id = id, onUpClick = { navController.popBackStack() })
            }
        }
    }
}

private const val startDestinationView = "welcome"
private const val cardListDestinationView = "cardList"
private const val cardDetailDestinationView = "cardDetail"