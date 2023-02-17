package com.example.magiccards.ui.navigation

import android.widget.ListView
import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.magiccards.ui.screens.cardlist.CardListView
import com.example.magiccards.ui.screens.welcome.Welcome

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  "welcome") {
        navigation(
            startDestination = "/welcome",
            route = "welcome"
        ) {
            composable("/welcome"){
                Welcome{
                    navController.navigate("/cardlist")
                }
            }
            composable("/cardlist"){
                CardListView{
                    navController.popBackStack()
                }
            }
        }
    }


}
