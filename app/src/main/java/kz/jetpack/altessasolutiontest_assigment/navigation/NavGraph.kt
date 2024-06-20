package kz.jetpack.altessasolutiontest_assigment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kz.jetpack.altessasolutiontest_assigment.data.repository.UserRepositoryImpl
import kz.jetpack.altessasolutiontest_assigment.di.RetrofitClient
import kz.jetpack.altessasolutiontest_assigment.presentation.ui.userDetailsScreen.UserDetailsScreen
import kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen.UserListScreen
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelFactory

@Composable
fun NavGraph(startDestination: String = "userList") {
    val navController = rememberNavController()

    val repository = UserRepositoryImpl(RetrofitClient.apiService)
    val viewModel: UserListViewModel = viewModel(
        factory = UserListViewModelFactory(repository)
    )
    val state by viewModel.state.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoadingInitial)

    NavHost(navController = navController, startDestination = startDestination) {
        composable("userList") {
            UserListScreen(navController)
        }
        composable("userDetails/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            if (username != null) {
                UserDetailsScreen(username = username, navController = navController, )
            } else {
                UserDetailsScreen(username = "No Name", navController = navController)
            }
        }
    }
}
