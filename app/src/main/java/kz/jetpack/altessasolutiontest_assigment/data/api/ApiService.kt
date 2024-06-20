package kz.jetpack.altessasolutiontest_assigment.data.api

import kz.jetpack.altessasolutiontest_assigment.data.modelDto.UserDto
import kz.jetpack.altessasolutiontest_assigment.data.modelDto.UserDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") perPage: Int): List<UserDto>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserDetailsDto
}
