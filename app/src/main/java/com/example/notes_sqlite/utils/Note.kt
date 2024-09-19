package com.example.notes_sqlite.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(val id:Int?=null,val title:String,val content:String) : Parcelable
