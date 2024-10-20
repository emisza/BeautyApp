package com.example.beautyapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_SECURITY_QUESTION = "security_question"
        private const val COLUMN_SECURITY_ANSWER = "security_answer"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT, " +
                "$COLUMN_SECURITY_QUESTION TEXT, " +
                "$COLUMN_SECURITY_ANSWER TEXT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SECURITY_QUESTION TEXT")
            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SECURITY_ANSWER TEXT")
        }
    }

    fun insertUser(username: String, password: String, securityQuestion: String, securityAnswer: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_SECURITY_QUESTION, securityQuestion)
            put(COLUMN_SECURITY_ANSWER, securityAnswer)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun readSecurityAnswer(username: String, securityAnswer: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_SECURITY_ANSWER = ?"
        val selectionArgs = arrayOf(username, securityAnswer)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)

        val isAnswerCorrect = cursor.count > 0
        cursor.close()
        return isAnswerCorrect
    }

    fun updatePassword(username: String, newPassword: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PASSWORD, newPassword)
        }
        db.update(TABLE_NAME, values, "$COLUMN_USERNAME = ?", arrayOf(username))
    }
}
