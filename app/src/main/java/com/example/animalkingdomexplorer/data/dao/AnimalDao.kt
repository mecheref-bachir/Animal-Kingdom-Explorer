package com.example.animalkingdomexplorer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorer.data.model.Animal

@Dao
interface AnimalDao {
    @Insert
    suspend fun insert(animal: Animal)

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>
}