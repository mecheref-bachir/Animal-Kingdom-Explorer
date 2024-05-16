package com.example.animalkingdomexplorer.data.repository

import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.SpeciesDao
import com.example.animalkingdomexplorer.data.model.Species

class SpeciesRepository(private val speciesDao: SpeciesDao) {
    val allSpecies: LiveData<List<Species>> = speciesDao.getAllSpecies()

    suspend fun insert(species: Species) {
        speciesDao.insert(species)
    }
}