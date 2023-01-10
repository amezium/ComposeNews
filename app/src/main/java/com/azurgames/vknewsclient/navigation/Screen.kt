package com.azurgames.vknewsclient.navigation

import android.net.Uri
import com.azurgames.vknewsclient.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {

    object NewsFeed: Screen(ROUTE_NEWS_FEED)

    object FavoriteFeed: Screen(ROUTE_FAVORITE_FEED)

    object ProfileFeed: Screen(ROUTE_PROFILE_FEED)

    object Home: Screen(ROUTE_HOME)

    object Comments: Screen(ROUTE_COMMENTS){

        private const val ROUTE_FOR_ARGS = "Comments"

        fun getRouteWithArgs(feedPost: FeedPost): String{
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object{
        const val KEY_FEED_POST = "feed_post"

        private const val ROUTE_HOME = "Home"
        private const val ROUTE_COMMENTS = "Comments/{$KEY_FEED_POST}"
        private const val ROUTE_NEWS_FEED = "NewsFeed"
        private const val ROUTE_FAVORITE_FEED = "FavoriteFeed"
        private const val ROUTE_PROFILE_FEED = "ProfileFeed"
    }
}

fun String.encode(): String{
    return Uri.encode(this)
}