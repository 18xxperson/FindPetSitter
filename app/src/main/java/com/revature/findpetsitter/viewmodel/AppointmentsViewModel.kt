package com.revature.findpetsitter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.revature.findpetsitter.data.Appointment
import com.revature.findpetsitter.repository.UserRepository
import kotlinx.coroutines.launch

class AppointmentsViewModel(appObj: Application): AndroidViewModel(appObj) {

    private val userRepository: UserRepository = UserRepository(appObj)



    fun fetchAllAppointments(): LiveData<List<Appointment>> {
        return userRepository.readAllAppointments
    }

    fun fetchAppointmentsById(id: Int) : LiveData<List<Appointment>> {
        return userRepository.getAppointmentsById(id)
    }

    fun insertAppointment(appointment: Appointment) {
        viewModelScope.launch {
            userRepository.insertAppointment(appointment)
        }
    }

    fun deleteAppointmentById(id:Int) {
        viewModelScope.launch {
            userRepository.deleteAppointmentById(id)
        }
    }

    fun updateAppointmentById(id:Int,start_date:String,end_date:String,total_price:Float) {
        viewModelScope.launch {
            userRepository.updateAppointmentById(id,start_date,end_date, total_price)
        }
    }

}