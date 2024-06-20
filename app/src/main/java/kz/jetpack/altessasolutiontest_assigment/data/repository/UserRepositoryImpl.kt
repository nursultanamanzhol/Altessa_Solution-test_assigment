package kz.jetpack.altessasolutiontest_assigment.data.repository

import kz.jetpack.altessasolutiontest_assigment.data.api.ApiService
import kz.jetpack.altessasolutiontest_assigment.data.mapper.toDomain
import kz.jetpack.altessasolutiontest_assigment.domain.model.User
import kz.jetpack.altessasolutiontest_assigment.domain.model.UserDetails
import kz.jetpack.altessasolutiontest_assigment.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun getUsers(since: Int, perPage: Int): List<User> {
        return apiService.getUsers(since, perPage).map { it.toDomain() }
    }

    override suspend fun getUser(username: String): UserDetails {
        return apiService.getUser(username).toDomain()
    }
}
