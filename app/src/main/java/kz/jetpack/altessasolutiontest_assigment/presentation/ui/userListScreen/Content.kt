package kz.jetpack.altessasolutiontest_assigment.presentation.ui.userListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModel
import kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel.UserListViewModelState
import kz.jetpack.altessasolutiontest_assigment.ui.theme.White
import kz.jetpack.altessasolutiontest_assigment.utils.CustomCircularProgressIndicator
import kz.jetpack.altessasolutiontest_assigment.utils.ErrorDialog

@Composable
fun Content(
    state: UserListViewModelState,
    listState: LazyListState,
    navController: NavController,
    viewModel: UserListViewModel
) {
    state.error?.let { errorMessage ->
        ErrorDialog(errorMessage, onDismiss = { viewModel.clearError() })
    } ?: LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.users) { user ->
            UserItem(user, onClick = {
                navController.navigate("userDetails/${user.login}")
            })
        }
        if (state.isLoadingMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CustomCircularProgressIndicator(color = White)
                }
            }
        }
    }
}