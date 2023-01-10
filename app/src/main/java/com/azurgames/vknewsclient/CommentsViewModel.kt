package com.azurgames.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azurgames.vknewsclient.domain.FeedPost
import com.azurgames.vknewsclient.domain.PostComment
import com.azurgames.vknewsclient.ui.theme.CommentsScreenState
import com.azurgames.vknewsclient.ui.theme.NewsFeedScreenState

class CommentsViewModel(feedPost: FeedPost): ViewModel() {


    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost){
        val comments = mutableListOf<PostComment>().apply {
            repeat(10){
                add(PostComment(id = it))
            }
        }
//        saveState = screenState.value
        _screenState.value = CommentsScreenState.Comments(feedPost, comments)
    }


}