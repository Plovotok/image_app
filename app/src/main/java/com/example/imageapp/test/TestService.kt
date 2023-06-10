package com.example.imageapp.test

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface TestService {

    @Headers("X-Jsio-Token: e2c702036017e36b03dcd29409c08c08")
    @GET("users")
    suspend fun getUsers() : Call<List<User>>
}