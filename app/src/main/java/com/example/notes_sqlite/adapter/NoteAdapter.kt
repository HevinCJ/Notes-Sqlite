package com.example.notes_sqlite.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes_sqlite.databinding.NoteRcviewLayoutBinding
import com.example.notes_sqlite.diffutil.DiffCallback
import com.example.notes_sqlite.utils.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

     var notes:List<Note> = emptyList()

    class NoteViewHolder(private val binding:NoteRcviewLayoutBinding):ViewHolder(binding.root){

        fun bindNote(note: Note){
            binding.apply {
                binding.note = note
                binding.executePendingBindings()
            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       return NoteViewHolder(NoteRcviewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = notes[position]
        holder.bindNote(currentNote)


    }


    fun setNote(note:List<Note>){
      val diffCallback = DiffCallback(notes,note)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.notes = note
        diffResult.dispatchUpdatesTo(this)
    }



}