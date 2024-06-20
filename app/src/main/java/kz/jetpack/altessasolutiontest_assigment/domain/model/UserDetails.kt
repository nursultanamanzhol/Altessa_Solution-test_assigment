package kz.jetpack.altessasolutiontest_assigment.domain.model

data class UserDetails(
    val login: String,
    val avatarUrl: String,
    val name: String?,
    val email: String?,
    val company: String?,
    val following: Int,
    val followers: Int,
    val createdAt: String
)