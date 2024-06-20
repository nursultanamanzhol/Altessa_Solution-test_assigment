package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kz.jetpack.altessasolutiontest_assigment.R
import kz.jetpack.altessasolutiontest_assigment.data.repository.UserRepositoryImpl
import kz.jetpack.altessasolutiontest_assigment.di.RetrofitClient
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelFactory
import kz.jetpack.altessasolutiontest_assigment.utils.CustomTopAppBar

@Composable
fun UserListScreen(navController: NavController) {
    val context = LocalContext.current

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val repository = UserRepositoryImpl(RetrofitClient.apiService)
    val viewModel: UserListViewModel = viewModel(
        factory = UserListViewModelFactory(repository)
    )
    val state by viewModel.state.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoadingInitial)

    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .filter { it != null && it.index == state.users.size - 1 }
            .collect {
                coroutineScope.launch {
                    viewModel.loadMoreUsers()
                }
            }
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = stringResource(id = R.string.ListScreen)) },
        content = { padding ->
            SwipeRefreshContent(
                state = state,
                listState = listState,
                swipeRefreshState = swipeRefreshState,
                padding = padding,
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}