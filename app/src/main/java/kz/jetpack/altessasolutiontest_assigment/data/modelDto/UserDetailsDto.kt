package kz.jetpack.altessasolutiontest_assigment.data.modelDto

data class UserDetailsDto(
    val login: String,
    val avatar_url: String,
    val name: String?,
    val email: String?,
    val company: String?,
    val following: Int,
    val followers: Int,
    val created_at: String
)