package com.example.imageapp.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.PasswordAuthentication

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var readAllData : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    suspend fun getUser(phone : String, pass : String) : User? {

        return repository.getUser(phone, pass)
    }

    suspend fun getUserByPhone(phone: String) : User? {
        return repository.getUserByPhone(phone)
    }

}