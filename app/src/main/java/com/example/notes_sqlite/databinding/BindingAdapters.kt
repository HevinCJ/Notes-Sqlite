package com.example.notes_sqlite.databinding

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.notes_sqlite.fragment.AddNoteDirections
import com.example.notes_sqlite.fragment.HomeDirections
import com.example.notes_sqlite.fragment.UpdateNoteArgs
import com.example.notes_sqlite.fragment.UpdateNoteDirections
import com.example.notes_sqlite.utils.Note

class BindingAdapters {

    companion object{


        @BindingAdapter("navigateToUpdateFrag")
        @JvmStatic
        fun navigateToUpdateFrag(view: ConstraintLayout,note:Note){
            view.setOnClickListener {
                val action = HomeDirections.actionHome2ToUpdateNote(note)
                view.findNavController().navigate(action)
            }
        }

    }


}