package com.example.animalkingdomexplorer.data.repository

import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorer.data.dao.AnimalDao
import com.example.animalkingdomexplorer.data.model.Animal

class AnimalRepository(private val animalDao: AnimalDao) {
    val allAnimals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    suspend fun insert(animal: Animal) {
        animalDao.insert(animal)
    }
}