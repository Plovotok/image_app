package com.example.imageapp.test

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String,

    @SerializedName("phone")
    val phone : String,

    @SerializedName("email")
    val email: String
)
