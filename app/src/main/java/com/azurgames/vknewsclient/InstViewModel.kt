package com.azurgames.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstViewModel: ViewModel() {

    private val _isFollowed = MutableLiveData<Boolean>()
    val isFollowed: LiveData<Boolean> = _isFollowed

    fun changeFollowingStatus(){
        val following = _isFollowed.value ?: false
        _isFollowed.value = !following
    }


}