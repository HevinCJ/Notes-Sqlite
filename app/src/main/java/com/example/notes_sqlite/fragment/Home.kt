package com.example.notes_sqlite.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_sqlite.R
import com.example.notes_sqlite.adapter.NoteAdapter
import com.example.notes_sqlite.database.NotesDatabaseHelper
import com.example.notes_sqlite.databinding.FragmentHomeBinding

class Home : Fragment() {
    private var home:FragmentHomeBinding?=null
    private val binding get() = home!!

    private lateinit var db:NotesDatabaseHelper

    private val noteAdapter:NoteAdapter by lazy { NoteAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        home = FragmentHomeBinding.inflate(layoutInflater,container,false)

        db = NotesDatabaseHelper(requireContext())

        setNoteAdapter()

        binding.apply {
            fltactionbtn.setOnClickListener {
                findNavController().navigate(R.id.action_home2_to_addNote)
            }

            Log.d("AllNotes",db.getAllNotes().toString())

            noteAdapter.setNote(db.getAllNotes())

        }

        setSwipeToDelete(binding.notesrcview)


        return binding.root
    }

    private fun setSwipeToDelete(notesrcview: RecyclerView) {

        val swipeToDelete = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val note = noteAdapter.notes[position]

                note.id?.let {
                    db.deleteNote(it)

                    val updatedNotes = noteAdapter.notes.toMutableList()
                    updatedNotes.removeAt(position)

                    noteAdapter.setNote(updatedNotes)

                    Toast.makeText(requireContext(), "Removed ${note.title}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        val itemtouchhelper = ItemTouchHelper(swipeToDelete)
        itemtouchhelper.attachToRecyclerView(notesrcview)
    }

    private fun setNoteAdapter() {
        binding.apply {
            notesrcview.adapter = noteAdapter
            notesrcview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }


}