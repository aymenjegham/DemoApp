package com.aymen.framework.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.aymen.core.domain.user.Id
import com.aymen.core.domain.user.Name
import com.aymen.core.domain.user.Picture
import com.aymen.core.domain.user.User
import com.aymen.framework.entity.UserEntity
import com.aymen.framework.global.helper.AppObjectMapper
import com.aymen.framework.global.utils.uuid
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@SmallTest
class UserDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var database: Database
    private lateinit var dao: UserDao

    @Inject
    lateinit var mapper: AppObjectMapper

    @Before
    fun setup() {
        hiltRule.inject()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAllUsers() = runTest {

        val userList = listOf(userItem1, userItem2)

        userList.map { user ->
            mapper.map(user, UserEntity::class).copy(
                firstName = user.name.first,
                lastName = user.name.last,
                title = user.name.title,
                idValue = user.id.idValue!!,
                large = user.picture.large,
                medium = user.picture.medium,
                thumbnail = user.picture.thumbnail
            )
        }.apply {
            dao.insertAll(this)
        }

        val allUsers = dao.getAll()


        val user1 = mapper.map(userItem1,UserEntity::class).copy(
            firstName = "Aymen",
            lastName = "Jegham",
            title = "Mr",
            idValue = "A145",
            large = "pic1",
            medium = "pic2",
            thumbnail = "pic3"
        )

        assertThat(allUsers).contains(user1)
    }

    @Test
    fun deleteAllUsers() = runTest {

        val userList = listOf(userItem1, userItem2)

        userList.map { user ->
            mapper.map(user, UserEntity::class).copy(
                firstName = user.name.first,
                lastName = user.name.last,
                title = user.name.title,
                idValue = user.id.idValue?: uuid(),
                large = user.picture.large,
                medium = user.picture.medium,
                thumbnail = user.picture.thumbnail
            )
        }.apply {
            dao.insertAll(this)
        }

        dao.deleteAll()


        val allUsers = dao.getAll()

        assertThat(allUsers).doesNotContain(userItem1)
        assertThat(allUsers).doesNotContain(userItem2)

    }

    companion object{
        val userItem1 = User(
            id = Id("A145"),
            phone = "12456789",
            name = Name("Aymen", "Jegham", "Mr"),
            email = "jegham@test.test",
            gender = "Male",
            nat = "Tn",
            picture = Picture("pic1", "pic2", "pic3")

        )
        val userItem2 = User(
            id = Id("A14scscs5"),
            phone = "156789",
            name = Name("Samer", "Jegham", "Mr"),
            email = "jegham2@test.test",
            gender = "Male",
            nat = "Tn",
            picture = Picture("pic1", "pic2", "pic3")
        )
    }


}