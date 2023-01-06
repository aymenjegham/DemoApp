package com.aymen.framework.dataSource.api

import retrofit2.http.GET
import retrofit2.http.Query


interface APIClient {

    @GET("?seed=foobar&results=30")
    suspend fun getUsers(@Query("page") page: Int): com.aymen.framework.entity.Result


}