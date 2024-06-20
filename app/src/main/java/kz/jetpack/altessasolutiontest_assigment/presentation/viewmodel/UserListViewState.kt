package kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel

import kz.jetpack.altessasolutiontest_assigment.domain.model.User

data class UserListViewModelState(
    val users: List<User> = emptyList(),
    val isLoadingInitial: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null
)
