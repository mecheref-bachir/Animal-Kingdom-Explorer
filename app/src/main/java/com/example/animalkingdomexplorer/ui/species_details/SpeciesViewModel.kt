package com.example.animalkingdomexplorer.ui.species_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.animalkingdomexplorer.data.database.AppDatabase
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.data.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SpeciesRepository
    val allSpecies: LiveData<List<Species>>

    init {
        val speciesDao = AppDatabase.getDatabase(application).speciesDao()
        repository = SpeciesRepository(speciesDao)
        allSpecies = repository.allSpecies
    }

    fun insert(species: Species) = viewModelScope.launch {
        repository.insert(species)
    }
}