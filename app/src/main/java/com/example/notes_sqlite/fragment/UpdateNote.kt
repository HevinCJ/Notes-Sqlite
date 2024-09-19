package com.example.notes_sqlite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes_sqlite.R
import com.example.notes_sqlite.database.NotesDatabaseHelper
import com.example.notes_sqlite.databinding.FragmentUpdateNoteBinding
import com.example.notes_sqlite.utils.Note


class UpdateNote : Fragment() {
    private var updatenote:FragmentUpdateNoteBinding?=null
    private val binding get() = updatenote!!

    private lateinit var db:NotesDatabaseHelper

    private val updateargs by navArgs<UpdateNoteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updatenote = FragmentUpdateNoteBinding.inflate(inflater,container,false)

        db = NotesDatabaseHelper(requireContext())


        binding.apply {

            setNoteFromArgs()

            updatebtn.setOnClickListener {
                val id = updateargs.note.id
                val title = edttexttitle.text.toString().trim()
                val content = edttextcontent.text.toString().trim()

                val note = Note(id = id, title=title, content = content)
                db.updateNote(note)
                Toast.makeText(requireContext(),"$title updated",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateNote_to_home2)
            }
        }



        return binding.root
    }

    private fun setNoteFromArgs() {

        binding.apply {
            edttexttitle.setText(updateargs.note.title)
            edttextcontent.setText(updateargs.note.content)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        updatenote = null
    }
}