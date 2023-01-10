package com.azurgames.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.azurgames.vknewsclient.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable ()-> Unit,
    favoriteScreenContent: @Composable ()-> Unit,
    profileScreenContent: @Composable ()-> Unit,
    commentsScreenContent: @Composable (FeedPost)-> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route){
        homeScreenNavGraph(newsFeedScreenContent, commentsScreenContent)
        composable(Screen.FavoriteFeed.route){
            favoriteScreenContent()
        }
        composable(Screen.ProfileFeed.route){
            profileScreenContent()
        }
    }
}