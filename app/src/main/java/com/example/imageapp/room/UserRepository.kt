package com.example.imageapp.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao : UserDao) {

    val readAllData : LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user : User) {
        userDao.regUser(user)
    }

    suspend fun getUser(phone : String, pass : String) : User? {
        return userDao.getUser(phone, pass)
    }

    suspend fun getUserByPhone(phone: String) : User? {
        return userDao.getUserByPhone(phone)
    }

}