package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.valentinilk.shimmer.shimmer
import kz.jetpack.altessasolutiontest_assigment.R
import kz.jetpack.altessasolutiontest_assigment.domain.model.User
import kz.jetpack.altessasolutiontest_assigment.ui.theme.TransparentWhite
import kz.jetpack.altessasolutiontest_assigment.utils.ShimmerBox
import kz.jetpack.altessasolutiontest_assigment.utils.ShimmerEffect
import kz.jetpack.altessasolutiontest_assigment.utils.UserId
import kz.jetpack.altessasolutiontest_assigment.utils.UserName

@Composable
fun UserItem(
    user: User,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val userIdText = context.getString(R.string.userId, user.id)
    var isLoading by remember { mutableStateOf(true) }
    var boolean by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(TransparentWhite)
        ) {
            SubcomposeAsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    ShimmerBox(modifier = Modifier.matchParentSize())
                },
                onSuccess = { isLoading = false },
                onError = { isLoading = false }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        if (isLoading) {
            ShimmerEffect(boolean)
        } else {
            Column {
                UserName(user.login)
                UserId(userIdText)
            }
        }
    }
}