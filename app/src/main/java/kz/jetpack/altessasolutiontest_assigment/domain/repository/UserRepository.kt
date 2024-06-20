package kz.jetpack.altessasolutiontest_assigment.domain.repository

import kz.jetpack.altessasolutiontest_assigment.domain.model.User
import kz.jetpack.altessasolutiontest_assigment.domain.model.UserDetails

interface UserRepository {
    suspend fun getUsers(since: Int, perPage: Int): List<User>
    suspend fun getUser(username: String): UserDetails
}
