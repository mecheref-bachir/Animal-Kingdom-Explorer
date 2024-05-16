package com.example.animalkingdomexplorer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species

@Dao
interface SpeciesDao {
    @Insert
    suspend fun insert(species: Species)

    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>
}