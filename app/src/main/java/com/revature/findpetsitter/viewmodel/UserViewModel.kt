package com.revature.findpetsitter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.revature.findpetsitter.data.User
import com.revature.findpetsitter.data.UserDatabase
import com.revature.findpetsitter.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application):AndroidViewModel(app) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(app).userDao()
        repository = UserRepository(app)
        readAllData = repository.readAllData
    }

    suspend fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun readAllData():LiveData<List<User>>
    {
        return repository.readAllData
    }

    fun readspecificuser(id:Int):LiveData<List<User>>
    {
        return repository.getspecifieduser(id)
    }

}