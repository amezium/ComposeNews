package com.azurgames.vknewsclient.domain

import com.azurgames.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_avatar,
    val commentText: String = "Long comment text",
    val publicationData: String = "14:00"
)
