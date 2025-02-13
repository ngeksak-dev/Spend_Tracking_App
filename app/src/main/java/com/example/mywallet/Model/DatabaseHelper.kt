package com.example.mywallet.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DatabaseHelper(context : Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "WalletAppDB.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "expenses"
        private const val COLUMN_ID = "id"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ("
        + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "$COLUMN_AMOUNT REAL, "
        + "$COLUMN_CATEGORY TEXT, "
        + "$COLUMN_DATE TEXT)")
        p0?.execSQL(createTableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }
    fun addSpending (spending: Spending): Long{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_AMOUNT, spending.amount)
            put(COLUMN_CATEGORY, spending.category)
            put(COLUMN_DATE, spending.date)
        }
        return db.insert(TABLE_NAME, null, values)
    }
    fun getAllSpendings(): List<Spending> {
        val spending = mutableListOf<Spending>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val amount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            spending.add(Spending(id, amount, category, date))
        }
        cursor.close()
        return spending
    }
}