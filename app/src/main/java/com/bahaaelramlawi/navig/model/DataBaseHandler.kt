package com.bahaaelramlawi.navig.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


//***********************************************************************************************************

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "UserTable"
        private val KEY_NAME = "name"
        private val KEY_MOBILE = "mobile"
        private val KEY_PASSWORD = "password"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_NAME + " TEXT," + KEY_MOBILE + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addUser(user: User):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, user.username)
        contentValues.put(KEY_MOBILE, user.mobile) // User Name
        contentValues.put(KEY_PASSWORD,user.password ) // User Phone
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    
    fun viewuser():List<User>{
        val userList:ArrayList<User> = ArrayList<User>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        var userMobile: String
        var userPassword: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userMobile = cursor.getString(cursor.getColumnIndex("mobile"))
                userPassword = cursor.getString(cursor.getColumnIndex("password"))
                val user= User(username = userName, mobile = userMobile, password = userPassword)
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }

    //method to update data
    fun updateuserloyee(user: User):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, user.username)
        contentValues.put(KEY_MOBILE, user.mobile) // User Name
        contentValues.put(KEY_PASSWORD,user.password ) // User Email

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"name="+user.username,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteuserloyee(user: User):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, user.username) // User UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"name="+user.username,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //***************************************************************************************************************
}