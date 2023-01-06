package com.aymen.framework.dataSource.api

import retrofit2.Response
import retrofit2.http.GET


interface APIClient {

    @GET("?results=10")
    suspend fun getAllUsers(): Response<com.aymen.framework.entity.Result>


}