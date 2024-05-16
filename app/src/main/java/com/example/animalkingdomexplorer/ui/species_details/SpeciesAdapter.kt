package com.example.animalkingdomexplorer.ui.species_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.data.model.Species
import com.example.animalkingdomexplorer.databinding.ItemSpeciesBinding

class SpeciesAdapter(private var species: List<Species>) : RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val binding = ItemSpeciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(species[position])
    }

    override fun getItemCount(): Int = species.size

    class SpeciesViewHolder(private val binding: ItemSpeciesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(species: Species) {
            binding.textViewSpeciesName.text = species.name
            binding.textViewSpeciesDescription.text = species.description
        }
    }

    fun updateSpecies(newSpecies: List<Species>) {
        val diffResult = DiffUtil.calculateDiff(SpeciesDiffCallback(species, newSpecies))
        this.species = newSpecies
        diffResult.dispatchUpdatesTo(this)
    }
}

class SpeciesDiffCallback(private val oldList: List<Species>, private val newList: List<Species>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}