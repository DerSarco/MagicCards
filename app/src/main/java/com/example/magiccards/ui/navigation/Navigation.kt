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
    NavHost(navController = navController, startDestination = "welcome") {
        navigation(
            startDestination = "/welcome",
            route = "welcome"
        ) {
            composable("/welcome") {
                Welcome {
                    navController.navigate("/cardList")
                }
            }
            composable("/cardList") {
                CardListView(
                    onItemClick = {
                        navController.navigate("/cardDetail/$it")
                    },
                    onUpClick = {
                        navController.popBackStack()

                    }
                )
            }
            composable("/cardDetail/{id}") {
                val id = it.arguments?.getString("id")
                requireNotNull(id)
                CardDetailView(id = id, onUpClick = { navController.popBackStack() })
            }
        }
    }
}
