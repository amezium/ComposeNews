package com.azurgames.vknewsclient.domain

import com.azurgames.vknewsclient.R

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationDate: String
)