package com.aymen.framework.repository.user

import androidx.paging.PagingData
import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow


class FakeUserListRepository : UserRepository {

    //    companion object{
//         val fakeListOfUsersItems = listOf(
//            User(
//                userId = Id("A145"),
//                phone = "12456789",
//                name = Name("Aymen", "Jegham", "Mr"),
//                email = "jegham@test.test",
//                gender = "Male",
//                nat = "Tn",
//                picture = Picture("pic1", "pic2", "pic3")
//
//            ),
//            User(
//                userId = Id("A14scscs5"),
//                phone = "156789",
//                name = Name("Samer", "Jegham", "Mr"),
//                email = "jegham2@test.test",
//                gender = "Male",
//                nat = "Tn",
//                picture = Picture("pic1", "pic2", "pic3")
//            )
//        )
//    }
//
//
//    private val response = Result(Result.Status.SUCCESS, fakeListOfUsersItems, null, null)
//
//
//    override fun getAllUsers(): Flow<Result<List<User>>?> {
//        return flow { emit(response) }
//
//    }
    override fun getUsers(): Flow<PagingData<User>> {
        TODO("Not yet implemented")
    }
}