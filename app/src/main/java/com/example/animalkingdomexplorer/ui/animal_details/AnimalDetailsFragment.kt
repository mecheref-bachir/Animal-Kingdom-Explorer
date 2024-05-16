package com.example.animalkingdomexplorer.ui.animal_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.databinding.FragmentAnimalDetailsBinding
import com.example.animalkingdomexplorer.ui.dialog.AnimalDialog

class AnimalDetailsFragment : Fragment(), AnimalDialog.OnAnimalSaveListener {

    private lateinit var binding: FragmentAnimalDetailsBinding

    private lateinit var viewModel: AnimalViewModel
    private lateinit var animalAdapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            val animalDialog = AnimalDialog(this@AnimalDetailsFragment)
            animalDialog.show(requireActivity().supportFragmentManager, "Add Animal")
        }

        // Observing changes in the animal list from the ViewModel
        viewModel.allAnimals.observe(viewLifecycleOwner) { animals ->
            animalAdapter.updateAnimals(animals)
        }
    }

    private fun setupRecyclerView() {
        animalAdapter = AnimalAdapter(listOf())  // Initialize with an empty list
        binding.rvAnimals.apply {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onAnimalSave(animal: Animal) {
        viewModel.insert(animal)
    }

}