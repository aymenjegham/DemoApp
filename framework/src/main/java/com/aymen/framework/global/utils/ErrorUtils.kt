package com.aymen.framework.global.utils


import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>, retrofit: Retrofit): com.aymen.core.domain.Error? {
        val converter = retrofit.responseBodyConverter<com.aymen.core.domain.Error>(com.aymen.core.domain.Error::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            com.aymen.core.domain.Error()
        }
    }
}