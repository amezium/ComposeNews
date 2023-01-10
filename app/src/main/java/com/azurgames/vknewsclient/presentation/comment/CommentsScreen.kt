package com.azurgames.vknewsclient.presentation.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azurgames.vknewsclient.domain.FeedPost
import com.azurgames.vknewsclient.domain.PostComment
import com.azurgames.vknewsclient.ui.theme.NewsClientTheme

@Composable
fun CommentsScree(onBackPress: () -> Unit, feedPost: FeedPost) {
    val viewModel: CommentsViewModel = viewModel(factory = CommentsViewModelFactory(feedPost))
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value
    if (currentState is CommentsScreenState.Comments){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Comments for feedPost id: ${currentState.feedPost.id}")
                    },
                    navigationIcon = {
                        IconButton(onClick = {onBackPress() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    }

                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
                contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp)
            ) {
                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    CommentItem(comment = comment)
                }
            }
        }
    }


}

@Composable
fun CommentItem(comment: PostComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = comment.authorAvatarId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${comment.authorName} id: ${comment.id}",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = comment.commentText,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = comment.publicationData,
            color = MaterialTheme.colors.onSecondary,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun PreviewComment() {
    NewsClientTheme() {
        CommentItem(comment = PostComment(0))
    }
}