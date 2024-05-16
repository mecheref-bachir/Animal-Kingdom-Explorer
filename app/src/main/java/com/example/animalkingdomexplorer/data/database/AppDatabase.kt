package com.example.animalkingdomexplorer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species

@Database(entities = [Animal::class, Species::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun speciesDao(): SpeciesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "animal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}