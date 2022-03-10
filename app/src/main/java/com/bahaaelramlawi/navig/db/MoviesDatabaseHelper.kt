package com.example.projectlistview.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bahaaelramlawi.navig.model.Movies

class MoviesDatabaseHelper(context: Context):
    SQLiteOpenHelper(context , "moviesdb" , null , 1 )
    {

        //Create a table to store MoviesList data
        override fun onCreate(db: SQLiteDatabase?)
            {
                db!!.execSQL(Movies.TABLE_CREATE)
            }

        //Function Database update
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
            {
                db!!.execSQL("DROP TABLE IF EXISTS ${Movies.TABLE_NAME}")
                onCreate(db)
            }

        //Function For get on all movies list
        fun getAllstudents()
            {
                var movie = ArrayList<Movies>()
            }
    }