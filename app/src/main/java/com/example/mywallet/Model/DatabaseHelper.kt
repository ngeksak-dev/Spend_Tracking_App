package com.example.mywallet.Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context :Context):
        SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
            companion object {
                private const val DATABASE_NAME = "mydatabase.db"
                private const val DATABASE_VERSION = 1
            }
    override fun onCreate(db: SQLiteDatabase) {
        // Create your database tables here
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${Spending.TABLE_NAME} (" +
    }
    }

        })