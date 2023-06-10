package com.example.imageapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val phone : String,

    val password : String
)
