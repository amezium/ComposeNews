package com.azurgames.vknewsclient.ui.theme

import com.azurgames.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    object Initial: NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}