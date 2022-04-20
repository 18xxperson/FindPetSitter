package com.revature.findpetsitter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.findpetsitter.dao.sitterDaO

/*@Database(entities = [],version = 1,exportSchema = false)
abstract class PetDatabase :RoomDatabase() {
    abstract fun sitterdao():sitterDaO
    companion object {
        @Volatile
        private var INSTANCE: PetDatabase? = null;
        fun getDatabase(context: Context): PetDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetDatabase::class.java, "mayur"
                ).build()

                INSTANCE = instance

                return instance
            }


        }
    }
}
*/