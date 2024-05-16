package com.example.animalkingdomexplorer.ui.animal_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.databinding.ItemAnimalBinding

class AnimalAdapter(private var animals: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    override fun getItemCount(): Int = animals.size

    class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            binding.textViewAnimalName.text = animal.name
            binding.textViewAnimalHabitat.text = animal.habitat
            binding.textViewAnimalDiet.text = animal.diet
        }
    }

    fun updateAnimals(newAnimals: List<Animal>) {
        val diffResult = DiffUtil.calculateDiff(AnimalDiffCallback(animals, newAnimals))
        this.animals = newAnimals
        diffResult.dispatchUpdatesTo(this)
    }
}

class AnimalDiffCallback(private val oldList: List<Animal>, private val newList: List<Animal>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}