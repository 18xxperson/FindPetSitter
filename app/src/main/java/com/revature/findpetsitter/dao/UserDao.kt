package com.revature.findpetsitter.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.revature.findpetsitter.data.Appointment
import com.revature.findpetsitter.data.Sitters
import com.revature.findpetsitter.data.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)


    @Query("SELECT * FROM User_list ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM User_list WHERE id == :id")
    fun getspecificuser(id: Int) :LiveData<User>
//    @Query("SELECT * FROM Sitters")
//    fun getSitters():LiveData<List<Sitters>>
//
//    @Query("SELECT * FROM Sitters WHERE type = :types")
//    fun getspecificSitters(types:String): LiveData<List<Sitters>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSitter(sitters: Sitters)
//
//    @Delete
//    fun deleteSitter(sitters: Sitters)

//    @Query("INSERT INTO appointment " +
////            "(user_id,sitter_id,start_date,end_date,service_type,total_price) "+
//            "VALUES (user_id,sitter_id,start_date,end_date,service_type,total_price")
//    suspend fun insertAppointment(user_id:Int,sitter_id:Int,start_date:String,end_date:String,service_type:String,total_price:Float)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointment")
    fun readAllAppointments():LiveData<List<Appointment>>

    @Query("SELECT * FROM appointment WHERE user_id=:id")
    fun getAppointmentsById(id:Int):LiveData<List<Appointment>>

    @Query("UPDATE appointment SET start_date=:start_date, end_date=:end_date, total_price=:total_price WHERE id=:id")
    suspend fun updateAppointmentById(id:Int,start_date:String,end_date:String,total_price:Float)

    @Query("DELETE FROM appointment WHERE id=:id")
    suspend fun deleteAppointmentById(id:Int)
}