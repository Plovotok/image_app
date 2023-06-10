package com.example.imageapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.net.UnknownServiceException


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun regUser(user : User)

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM users WHERE phone = :phone AND password = :pass")
    suspend fun getUser(phone : String, pass : String) : User?

    @Query("SELECT * FROM users WHERE phone = :phone")
    suspend fun getUserByPhone(phone: String) : User?

}