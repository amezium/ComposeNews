package com.azurgames.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azurgames.vknewsclient.domain.FeedPost
import com.azurgames.vknewsclient.domain.StatisticItem
import com.azurgames.vknewsclient.ui.theme.NewsFeedScreenState

class NewsFeedViewModel : ViewModel() {



    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(13){
            (add(FeedPost(id = it)))
        }
    }

    private val initialState = NewsFeedScreenState.Posts(sourceList)


    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    fun updateCount(feedPost: FeedPost,item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPost = currentState.posts.toMutableList()
        val oldStatistic = feedPost.statistics
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else oldItem
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistic)
        val newPost = oldPost.apply {
            replaceAll {
                if (it.id == newFeedPost.id){
                    newFeedPost
                }else{
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPost)
    }

    fun remove(feedPost: FeedPost){
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPost = currentState.posts.toMutableList()
        oldPost.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPost)
    }

}