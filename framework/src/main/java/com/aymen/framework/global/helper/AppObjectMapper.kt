package com.aymen.framework.global.helper


import com.aymen.core.domain.user.User
import com.aymen.core.helper.ObjectMapper
import com.aymen.framework.entity.UserEntity
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class AppObjectMapper @Inject constructor(gson: Gson) : ObjectMapper(gson) {


    override val supportedMapping: Map<KClass<out Any>, KClass<out Any>>
        get() = mapOf(
            UserEntity::class to User::class,
            User::class to UserEntity::class,
        )
}