package kz.jetpack.altessasolutiontest_assigment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.jetpack.altessasolutiontest_assigment.domain.model.UserDetails
import kz.jetpack.altessasolutiontest_assigment.domain.repository.UserRepository

class UserDetailsViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userDetails = MutableStateFlow<UserDetails?>(null)
    val userDetails: StateFlow<UserDetails?> = _userDetails

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchUserDetails(username: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val user = userRepository.getUser(username)
                _userDetails.value = user
            } catch (e: Exception) {
                _error.value = "Error fetching user details: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
