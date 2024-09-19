package com.example.notes_sqlite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes_sqlite.R
import com.example.notes_sqlite.database.NotesDatabaseHelper
import com.example.notes_sqlite.databinding.FragmentAddNoteBinding
import com.example.notes_sqlite.utils.Note


class AddNote : Fragment() {
    private var addnote:FragmentAddNoteBinding?=null
    private val binding get()  = addnote!!

    private lateinit var db:NotesDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addnote = FragmentAddNoteBinding.inflate(inflater,container,false)

        db = NotesDatabaseHelper(requireContext())

        binding.apply {
            savebtn.setOnClickListener {
                val title = edttexttitle.text.toString().trim()
                val content = edttextcontent.text.toString().trim()

                if (title.isNotEmpty() && content.isNotEmpty()){
                    val note = Note(title = title, content = content)
                    db.insertNote(note)
                    db.close()
                    findNavController().navigate(R.id.action_addNote_to_home2)
                    Toast.makeText(requireContext(),"Successfully Added $title",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(),"Fill required fields",Toast.LENGTH_LONG).show()
                }

            }
        }





        return binding.root

    }


}



