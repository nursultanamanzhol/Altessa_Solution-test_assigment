package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelState
import kz.jetpack.altessasolutiontest_assigment.ui.theme.BackgroundColorScreens

@Composable
fun SwipeRefreshContent(
    state: UserListViewModelState,
    listState: LazyListState,
    swipeRefreshState: SwipeRefreshState,
    padding: PaddingValues,
    navController: NavController,
    viewModel: UserListViewModel
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.clearExceptFirstTen()
            viewModel.refreshUsers()
            swipeRefreshState.isRefreshing = false
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Box(
            Modifier
                .background(BackgroundColorScreens)
                .fillMaxSize()
        ) {
            Content(state = state, listState = listState, navController = navController, viewModel = viewModel)
            if (state.isLoadingInitial) {
                InitialLoading(swipeRefreshState)
            }
        }
    }
}