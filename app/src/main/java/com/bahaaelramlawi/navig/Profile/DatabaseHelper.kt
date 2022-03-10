package com.bahaaelramlawi.navig.Profile

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Button


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    private var p0: SQLiteDatabase

    init {
        p0 = this.readableDatabase
    }


    companion object {
        const val DATABASE_NAME = "profiledb"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Profile"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(profile.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${profile.TABLE_NAME}")
        onCreate(db)
    }

    fun saveProfile(pr: Button, name: String, phone: Int, password: String): Boolean {

        val cv = ContentValues()
        cv.put(profile.COL_NAME, name)
        cv.put(profile.COL_PHONE, phone)
        cv.put(profile.COL_PASSWORD, password)
        return p0.insert(profile.TABLE_NAME, null, cv) > 0

    }
}





