package kz.jetpack.altessasolutiontest_assigment.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kz.jetpack.altessasolutiontest_assigment.data.repository.UserRepositoryImpl
import kz.jetpack.altessasolutiontest_assigment.di.RetrofitClient
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelFactory
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelState

@Composable
fun rememberUserListViewModel(): UserListViewModel {
    val repository = UserRepositoryImpl(RetrofitClient.apiService)
    return viewModel(
        factory = UserListViewModelFactory(repository)
    )
}

@Composable
fun rememberUserListState(viewModel: UserListViewModel): UserListViewModelState {
    return viewModel.state.collectAsState().value
}

@Composable
fun rememberSwipeRefreshState(viewModel: UserListViewModel): SwipeRefreshState {
    val state by viewModel.state.collectAsState()
    return rememberSwipeRefreshState(isRefreshing = state.isLoadingInitial)
}
