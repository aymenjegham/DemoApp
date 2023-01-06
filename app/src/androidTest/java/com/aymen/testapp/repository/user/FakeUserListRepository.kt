package com.aymen.testapp.repository.user

import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.domain.Result
import com.aymen.core.domain.user.Id
import com.aymen.core.domain.user.Name
import com.aymen.core.domain.user.Picture
import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeUserListRepository(
    private val errorValue: Boolean,
) : UserRepository {

    private var shouldReturnNetworkError = false


    private val response = Result(Result.Status.SUCCESS, fakeListOfUsersItems, null, null)

    private val responseFailure =
        Result(Result.Status.ERROR, fakeListOfUsersItems, null, null)

    override fun getAllUsers(): Flow<Result<List<User>>?> {
        return flow {
            if (errorValue) {
                emit(responseFailure)
            } else {
                emit(response)
            }
        }

    }

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    companion object {
        val fakeListOfUsersItems = listOf(
            User(
                id = Id("A145"),
                phone = "12456789",
                name = Name("Aymen", "Jegham", "Mr"),
                email = "jegham@test.test",
                gender = "Male",
                nat = "Tn",
                picture = Picture("pic1", "pic2", "pic3")

            ),
            User(
                id = Id("A14scscs5"),
                phone = "156789",
                name = Name("Samer", "Jegham", "Mr"),
                email = "jegham2@test.test",
                gender = "Male",
                nat = "Tn",
                picture = Picture("pic1", "pic2", "pic3")
            )
        )
    }
}