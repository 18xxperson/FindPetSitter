package com.revature.findpetsitter.repository

import androidx.lifecycle.LiveData
import com.revature.findpetsitter.dao.UserDao
import com.revature.findpetsitter.data.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }


}