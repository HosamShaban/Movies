package com.bahaaelramlawi.navig.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.bahaaelramlawi.navig.model.User

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, "UserDatabase",null,1) {

    companion object
        {
            val tableUser = "UserTable"
            val logName = "name"
            val logMobile = "mobile"
            val logPassword = "password"
        }
    //////////////////////////////////////////////////////////////

    //Create a table to store user data
    override fun onCreate(db: SQLiteDatabase?)
        {
            val logTable = ("CREATE TABLE " + tableUser + "(" + logName + " TEXT," + logMobile + " TEXT," + logPassword + " TEXT" + ")")
            db?.execSQL(logTable) //To implement for logTable
        }
    //////////////////////////////////////////////////////////////

    //Function Database update
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
        {
            val logTable = "DROP TABLE IF EXISTS tableUser"
            db!!.execSQL(logTable)
            onCreate(db)
        }
    /////////////////////////////////////////////////////////////

    //Function to Add Data
    fun addUser(user: User):Long
        {
            val db = this.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(logName,user.username) // UserName
            contentValues.put(logMobile,user.mobile) // UserMobile
            contentValues.put(logPassword,user.password ) // UserPassword
            return db.insert(tableUser, null, contentValues)
        }

    ////////////////////////////////////////////////////////////////////

    //Function to Read Data
    fun viewuser():List<User>
        {
            val userList:ArrayList<User> = ArrayList<User>()
            val db = this.readableDatabase
            val sQ = "SELECT * FROM $tableUser"
            var cursor: Cursor? = null ///////////

            /////////////////////////////////////////////////////////
            try
                {
                    cursor = db.rawQuery(sQ, null)
                }
            catch (e: SQLiteException)
                {
                    db.execSQL(sQ)
                    return ArrayList()
                }
            /////////////////////////////////////////////////////////

            var userName: String
            var userMobile: String
            var userPassword: String

            if (cursor.moveToFirst())
            {
                do
                {
                    userName = cursor.getString(cursor.getColumnIndex("name"))
                    userMobile = cursor.getString(cursor.getColumnIndex("mobile"))
                    userPassword = cursor.getString(cursor.getColumnIndex("password"))
                    val user= User(username = userName, mobile = userMobile, password = userPassword)
                    userList.add(user)
                }
                while (cursor.moveToNext())
            }
            return userList
        }
}