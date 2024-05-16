package com.example.animalkingdomexplorer.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AnimalDialog(val onAnimalSaveListener: OnAnimalSaveListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_animal, null)

        val name = view.findViewById<TextInputEditText>(R.id.tiet_name)
        val habitat = view.findViewById<TextInputEditText>(R.id.tiet_habitat)
        val diet = view.findViewById<TextInputEditText>(R.id.tiet_diet)

        val nameTextInputLayout = view.findViewById<TextInputLayout>(R.id.til_name)
        val habitatTextInputLayout = view.findViewById<TextInputLayout>(R.id.til_habitat)
        val dietTextInputLayout = view.findViewById<TextInputLayout>(R.id.til_diet)


        builder.setView(view)
            .setPositiveButton("OK") { dialog, _ ->
                // Handle positive button click
                var isValid = true
                var errorMessage = ""
                if (name.text.toString().isBlank()) {
                    errorMessage += "Name shouldn't be blank\n"
                    isValid = false
                }
                if (habitat.text.toString().isBlank()) {
                    errorMessage += "Habitat shouldn't be blank\n"
                    isValid = false
                }
                if (diet.text.toString().isBlank()) {
                    errorMessage += "Diet shouldn't be blank\n"
                    isValid = false
                }

                if (!isValid) {
                    Toast.makeText((onAnimalSaveListener as Fragment).requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                } else {
                    val animal = Animal(name = name.text.toString(), habitat = habitat.text.toString(), diet = diet.text.toString())
                    onAnimalSaveListener.onAnimalSave(animal)
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle negative button click
                dialog.dismiss()
            }

        return builder.create()
    }

    interface OnAnimalSaveListener {
        fun onAnimalSave(animal: Animal)
    }
}