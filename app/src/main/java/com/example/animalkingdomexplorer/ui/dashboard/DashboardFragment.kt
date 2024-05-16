package com.example.animalkingdomexplorer.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDashboardBinding = FragmentDashboardBinding.inflate(layoutInflater)

        binding.mbAnimals.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_animalDetails)
        }

        binding.mbSpecies.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_speciesDetails)
        }

        return binding.root
    }

}