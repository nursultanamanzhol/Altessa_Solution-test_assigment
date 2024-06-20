package kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.jetpack.altessasolutiontest_assigment.domain.model.User
import kz.jetpack.altessasolutiontest_assigment.domain.repository.UserRepository

class UserListViewModel(private val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow(UserListViewModelState())
    val state: StateFlow<UserListViewModelState> = _state

    private var currentPage = 0
    private val perPage = 10

    fun fetchUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingInitial = true, error = null)
            try {
                val fetchedUsers = repository.getUsers(currentPage, perPage)
                currentPage += fetchedUsers.size
                _state.value = _state.value.copy(
                    users = (_state.value.users + fetchedUsers).distinctBy { it.id },
                    isLoadingInitial = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Error fetching users: ${e.message}",
                    isLoadingInitial = false
                )
            }
        }
    }

    fun loadMoreUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingMore = true, error = null)
            try {
                val fetchedUsers = repository.getUsers(currentPage, perPage)
                currentPage += fetchedUsers.size
                _state.value = _state.value.copy(
                    users = (_state.value.users + fetchedUsers).distinctBy { it.id },
                    isLoadingMore = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Error loading more users: ${e.message}",
                    isLoadingMore = false
                )
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    fun clearExceptFirstTen() {
        _state.value = _state.value.copy(users = _state.value.users.take(10))
        currentPage = 10
    }

    fun refreshUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingInitial = true, error = null)
            try {
                val refreshedUsers = repository.getUsers(0, perPage)
                _state.value = _state.value.copy(
                    users = refreshedUsers,
                    isLoadingInitial = false
                )
                currentPage = refreshedUsers.size
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Error refreshing users: ${e.message}",
                    isLoadingInitial = false
                )
            }
        }
    }
}
