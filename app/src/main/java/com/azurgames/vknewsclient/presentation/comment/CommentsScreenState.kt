package com.azurgames.vknewsclient.presentation.comment

import com.azurgames.vknewsclient.domain.FeedPost
import com.azurgames.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object Initial: CommentsScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): CommentsScreenState()
}