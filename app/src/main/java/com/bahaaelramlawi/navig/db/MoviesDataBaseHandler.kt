package com.example.projectlistview.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.bahaaelramlawi.navig.model.Movies


class MoviesDataBaseHandler(context: Context, database_name : String): SQLiteOpenHelper(context,database_name,null,1) {

    companion object
        {
            private val moviesTable = "movies_table"
            private val movieName = "name"
            private val movieYear = "year"
            private val movieInfo = "info"
            private val movieImage = "image"
        }
    /////////////////////////////////////////////////////////////////

    //Create table to store movie data
    override fun onCreate(db: SQLiteDatabase?)
        {
            val movieTable = ("CREATE TABLE " + moviesTable + "(" + movieName + " TEXT," + movieYear + " TEXT," + movieInfo + " TEXT," + movieImage + " INTEGER"+ ")")
            db?.execSQL(movieTable)
        }

    //////////////////////////////////////////////////////////////////

    //Function Database update
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
        {
            val logTable = "DROP TABLE IF EXISTS movies_table"
            db!!.execSQL(logTable)
            onCreate(db)
        }

    //////////////////////////////////////////////////////////////////

    //Function to Add Data
    fun addUser(user: Movies):Long
        {
            val db = this.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(movieName, user.name) // MovieName
            contentValues.put(movieYear, user.year) // MovieYear
            contentValues.put(movieInfo,user.info ) // MovieInfo
            contentValues.put(movieImage,user.image ) // MovieImage

            return db.insert(moviesTable, null, contentValues) //Inserting Row
        }

    ///////////////////////////////////////////////////////////////////////////

    //method to read data
    fun getMovies():List<Movies>
        {

            val userList:ArrayList<Movies> = ArrayList<Movies>()

            val db = this.readableDatabase
            val sQ = "SELECT * FROM $moviesTable"
            var cursor: Cursor? = null

            ////////////////////////////////////////////////////
            try
                {
                    cursor = db.rawQuery(sQ, null)
                }
            catch (e: SQLiteException)
                {
                    db.execSQL(sQ)
                    return ArrayList()
                }
            ///////////////////////////////////////////////////

            var userName: String
            var userYear: String
            var userInfo: String
            var userImage: Int

            if (cursor.moveToFirst())
                {
                    do
                        {
                            userName = cursor.getString(cursor.getColumnIndex("name"))
                            userYear = cursor.getString(cursor.getColumnIndex("year"))
                            userInfo = cursor.getString(cursor.getColumnIndex("info"))
                            userImage = cursor.getInt(cursor.getColumnIndex("image"))

                            val user= Movies(name = userName, year = userYear,info= userInfo,image = userImage)
                            userList.add(user)
                        }
                    while (cursor.moveToNext())
                }
            return userList
        }

    ///////////////////////////////////////////////////////////////////////

    //Function To Delete Movie Data
    fun deleteUser(user: Movies):Int
        {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(movieName, user.image) // User MovieImage
            return db.delete(moviesTable, "image=" + user.image, null) // Deleting Row
        }

    ///////////////////////////////////////////////////////////////////////
}