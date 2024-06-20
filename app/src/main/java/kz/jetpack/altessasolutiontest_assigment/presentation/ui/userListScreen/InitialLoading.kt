package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kz.jetpack.altessasolutiontest_assigment.ui.theme.BackgroundColorScreens
import kz.jetpack.altessasolutiontest_assigment.ui.theme.White
import kz.jetpack.altessasolutiontest_assigment.utils.CustomCircularProgressIndicator

@Composable
fun InitialLoading(swipeRefreshState: SwipeRefreshState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColorScreens),
        contentAlignment = Alignment.Center
    ) {
        swipeRefreshState.isRefreshing = false
        CustomCircularProgressIndicator(color = White)
    }
}