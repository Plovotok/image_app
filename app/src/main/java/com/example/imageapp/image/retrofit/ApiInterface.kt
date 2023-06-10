package com.example.imageapp.image.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("test-header: test")
    @GET("test/get-pictures")
    fun getData() : Call<ImageDataClass>

    companion object {
        var BASE_URL = "https://api.yooyo.ru/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    fun getImageInfo(data : ImageDataClass) : List<Data> {
        return data.data
    }

}