package com.example.animalkingdomexplorer.ui.animal_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.animalkingdomexplorer.data.database.AppDatabase
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AnimalRepository
    val allAnimals: LiveData<List<Animal>>

    init {
        val animalDao = AppDatabase.getDatabase(application).animalDao()
        repository = AnimalRepository(animalDao)
        allAnimals = repository.allAnimals
    }

    fun insert(animal: Animal) = viewModelScope.launch {
        repository.insert(animal)
    }
}