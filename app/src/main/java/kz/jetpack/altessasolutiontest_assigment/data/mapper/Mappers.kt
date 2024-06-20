package kz.jetpack.altessasolutiontest_assigment.data.mapper

import kz.jetpack.altessasolutiontest_assigment.data.modelDto.UserDto
import kz.jetpack.altessasolutiontest_assigment.data.modelDto.UserDetailsDto
import kz.jetpack.altessasolutiontest_assigment.domain.model.User
import kz.jetpack.altessasolutiontest_assigment.domain.model.UserDetails

fun UserDto.toDomain() = User(
    login = login,
    id = id,
    avatarUrl = avatar_url
)

fun UserDetailsDto.toDomain() = UserDetails(
    login = login,
    avatarUrl = avatar_url,
    name = name,
    email = email,
    company = company,
    following = following,
    followers = followers,
    createdAt = created_at
)
