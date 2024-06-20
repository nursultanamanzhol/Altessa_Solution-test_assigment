package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import kz.jetpack.altessasolutiontest_assigment.domain.model.UserDetails
import kz.jetpack.altessasolutiontest_assigment.ui.theme.TransparentWhite
import kz.jetpack.altessasolutiontest_assigment.utils.ShimmerBox
import kz.jetpack.altessasolutiontest_assigment.utils.ShimmerEffect
import kz.jetpack.altessasolutiontest_assigment.utils.UserData
import kz.jetpack.altessasolutiontest_assigment.utils.UserName

@Composable
fun UserDetailsContent(userDetails: UserDetails) {
    var isLoading by remember { mutableStateOf(true) }
    var boolean by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(TransparentWhite)
        ) {
            SubcomposeAsyncImage(
                model = userDetails.avatarUrl,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    ShimmerBox(modifier = Modifier.matchParentSize())
                },
                onSuccess = { isLoading = false },
                onError = { isLoading = false }
            )
        }
        if (isLoading) {
            ShimmerEffect(boolean)
        } else {
            Spacer(modifier = Modifier.height(16.dp))
            UserName(text = userDetails.name ?: "No Name")
            UserData(text = userDetails.email ?: "No Email")
            UserData(text = userDetails.company ?: "No Organization")
            UserData(text = "Following: ${userDetails.following}")
            UserData(text = "Followers: ${userDetails.followers}")
            UserData(text = "Created at: ${userDetails.createdAt}")
        }
    }
}
