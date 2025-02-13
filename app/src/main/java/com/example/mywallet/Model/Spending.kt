package com.example.mywallet.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Spending (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val category: String,
    val date: String = getCurrentDate()
){
    companion object{
        fun getCurrentDate(): String{
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = Date()
            return dateFormat.format(currentDate)
        }
    }
}