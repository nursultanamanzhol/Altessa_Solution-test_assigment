package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kz.jetpack.altessasolutiontest_assigment.R
import kz.jetpack.altessasolutiontest_assigment.data.repository.UserRepositoryImpl
import kz.jetpack.altessasolutiontest_assigment.di.RetrofitClient
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserDetailsViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserDetailsViewModelFactory
import kz.jetpack.altessasolutiontest_assigment.ui.theme.BackgroundColorScreens
import kz.jetpack.altessasolutiontest_assigment.utils.CustomTopAppBar
import kz.jetpack.altessasolutiontest_assigment.utils.ErrorDialog

@Composable
fun UserDetailsScreen(username: String, navController: NavController) {
    val repository = UserRepositoryImpl(RetrofitClient.apiService)
    val viewModel: UserDetailsViewModel = viewModel(
        factory = UserDetailsViewModelFactory(repository)
    )
    val userDetails by viewModel.userDetails.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(username) {
        viewModel.fetchUserDetails(username)
    }

    error?.let { errorMessage ->
        ErrorDialog(errorMessage = errorMessage, onDismiss = { viewModel.clearError() })
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = stringResource(id = R.string.DetailsUser), onBackClick = { navController.popBackStack() }) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColorScreens)
                    .padding(padding)
            ) {
                when {
                    loading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }

                    error == null && userDetails != null -> {
                        UserDetailsContent(userDetails = userDetails!!)
                    }
                }
            }
        }
    )
}

