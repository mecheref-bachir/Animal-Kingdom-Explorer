package com.example.animalkingdomexplorer.ui.species_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.databinding.FragmentSpeciesDetailsBinding
import com.example.animalkingdomexplorer.ui.dialog.AnimalDialog
import com.example.animalkingdomexplorer.ui.dialog.SpeciesDialog

class SpeciesDetailsFragment : Fragment(), SpeciesDialog.OnSpeciesSaveListener {

    private lateinit var binding: FragmentSpeciesDetailsBinding

    private lateinit var viewModel: SpeciesViewModel
    private lateinit var speciesAdapter: SpeciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpeciesDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpeciesViewModel::class.java)

        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            val speciesDialog = SpeciesDialog(this@SpeciesDetailsFragment)
            speciesDialog.show(requireActivity().supportFragmentManager, "Add Species")
        }

        viewModel.allSpecies.observe(viewLifecycleOwner) { species ->
            speciesAdapter.updateSpecies(species)
        }
    }

    private fun setupRecyclerView() {
        speciesAdapter = SpeciesAdapter(listOf())  // Initialize with an empty list
        binding.rvSpecies.apply {
            adapter = speciesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onSpeciesSave(species: Species) {
        viewModel.insert(species)
    }

}