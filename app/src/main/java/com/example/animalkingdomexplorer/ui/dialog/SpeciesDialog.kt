package com.example.animalkingdomexplorer.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.animalkingdomexplorer.R
import com.example.animalkingdomexplorer.data.model.Animal
import com.example.animalkingdomexplorer.data.model.Species
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SpeciesDialog(val onSpeciesSaveListener: OnSpeciesSaveListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_species, null)

        val name = view.findViewById<TextInputEditText>(R.id.tiet_name)
        val description = view.findViewById<TextInputEditText>(R.id.tiet_description)

        val nameTextInputLayout = view.findViewById<TextInputLayout>(R.id.til_name)
        val descriptionTextInputLayout = view.findViewById<TextInputLayout>(R.id.til_description)

        builder.setView(view)
            .setPositiveButton("OK") { dialog, _ ->
                // Handle positive button click
                var isValid = true
                var errorMessage = ""
                if (name.text.toString().isBlank()) {
                    errorMessage += "Name shouldn't be blank\n"
                    isValid = false
                }
                if (description.text.toString().isBlank()) {
                    errorMessage += "Name shouldn't be blank\n"
                    isValid = false
                }

                if (!isValid) {
                    Toast.makeText((onSpeciesSaveListener as Fragment).requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                } else {
                    val species = Species(name = name.text.toString(), description = description.text.toString())
                    onSpeciesSaveListener.onSpeciesSave(species)
                }




            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle negative button click
                dialog.dismiss()
            }
        return builder.create()
    }

    interface OnSpeciesSaveListener {
        fun onSpeciesSave(species: Species)
    }
}