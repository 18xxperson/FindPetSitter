package com.revature.findpetsitter.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.findpetsitter.dao.UserDao
import com.revature.findpetsitter.data.Appointment
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.data.User
import com.revature.findpetsitter.data.UserDatabase

class UserRepository(application: Application) {

    private var userDao:UserDao
    init {
        var database= UserDatabase.getDatabase(application)
        userDao = database.userDao()
    }

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val readAllAppointments: LiveData<List<Appointment>> = userDao.readAllAppointments()

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    //Appointments operations
    suspend fun insertAppointment(appointment:Appointment) {
        userDao.insertAppointment(appointment)
    }

    suspend fun deleteAppointmentById(id:Int) {
        userDao.deleteAppointmentById(id)
    }

    suspend fun updateAppointmentById(id:Int,start_date:String,end_date:String,total_price:Float) {
        userDao.updateAppointmentById(id,start_date,end_date,total_price)
    }

    fun getAppointmentsById(id:Int):LiveData<List<Appointment>> {
        return userDao.getAppointmentsById(id)
    }


    //sitters operations

//    fun fetchsittertype(type:String): LiveData<List<Sitters>>
//     {
//         return userDao.getspecificSitters(types = type)
//      }
//
//     fun sitters()=userDao.getSitters()
//
//       suspend fun insertsitter(sitters: Sitters)
//      {
//         userDao.insertSitter(sitters)
//     }


}