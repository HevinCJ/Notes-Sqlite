package com.example.notes_sqlite.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.notes_sqlite.utils.Note

class DiffCallback(private val oldlist:List<Note>, private val newlist:List<Note>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldlist[oldItemPosition].id == newlist[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldlist[oldItemPosition]==newlist[newItemPosition]
    }


}