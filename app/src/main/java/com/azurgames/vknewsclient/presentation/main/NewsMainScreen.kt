package com.azurgames.vknewsclient

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.azurgames.vknewsclient.navigation.AppNavGraph
import com.azurgames.vknewsclient.navigation.NavigationItem
import com.azurgames.vknewsclient.navigation.rememberNavigationState
import com.azurgames.vknewsclient.presentation.comment.CommentsScree
import com.azurgames.vknewsclient.ui.theme.HomeScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(paddingValues = it, onCommentClickListener = {
                    navigationState.navigateToComments(it)
                })
            },
            favoriteScreenContent = { Text(text = "Favourite", color = Color.Black) },
            profileScreenContent = { Text(text = "Profile", color = Color.Black) },
            commentsScreenContent = {
                CommentsScree(onBackPress = {
                    navigationState.navHostController.popBackStack()
                }, feedPost = it)
            }
        )

    }
}