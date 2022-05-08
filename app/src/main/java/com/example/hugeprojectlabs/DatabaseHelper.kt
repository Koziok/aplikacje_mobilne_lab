package com.example.hugeprojectlabs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private const val DATABASE_VER = 1
        private const val DATABASE_NAME = "Database.db"

        private const val TABLE_NAME = "Tracks"
        private const val COL_ID = "Track_Id"
        private const val COL_TRACK_NAME = "Track_Name"
        private const val COL_DESC = "Description"
        private const val COL_TIME_REQ = "Time_Required"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_TRACK_NAME VARCHAR(50), $COL_DESC TEXT, $COL_TIME_REQ TIME(5));")
        db.execSQL("insert into $TABLE_NAME ($COL_TRACK_NAME, $COL_DESC, $COL_TIME_REQ) values ('Trasa brzegiem Warty', 'Urokliwa trasa biegnąca brzegiem rzeki Warty.', '00:45');")
        db.execSQL("insert into $TABLE_NAME ($COL_TRACK_NAME, $COL_DESC, $COL_TIME_REQ) values ('Trasa obrzeżami Poznania', 'Relaksująca i widokowa. Coś dla każdego', '00:45');")
        db.execSQL("insert into $TABLE_NAME ($COL_TRACK_NAME, $COL_DESC, $COL_TIME_REQ) values ('Trasa w centrum miasta', 'Trasa dla pasjonatów miejskiej dżungli.', '00:45');")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE_NAME;")
        onCreate(db)
    }

    fun append(arr: Array<String?>, element: String): Array<String?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }

    fun showTracks() : Array<String?> {
        val db = this.writableDatabase
        var returnList = arrayOfNulls<String?>(0)
        var tempString =""
        val search = "SELECT * FROM $TABLE_NAME"
        db.rawQuery(search, arrayOf()).use {
            if (it.moveToFirst()) {
                do{
                    tempString = it.getString(0) + it.getString(1) + it.getString(2) + it.getString(3)+"\n"
                    returnList = append(returnList, tempString)
                } while (it.moveToNext())
            }
        }

        db.close()
        return returnList
    }
}