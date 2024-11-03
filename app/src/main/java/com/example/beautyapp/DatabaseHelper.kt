package com.example.beautyapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.mindrot.jbcrypt.BCrypt

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_SECURITY_QUESTION TEXT,
                $COLUMN_SECURITY_ANSWER TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SECURITY_QUESTION TEXT")
            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_SECURITY_ANSWER TEXT")
        }
    }

    fun insertUser(username: String, password: String, securityQuestion: String, securityAnswer: String): Long {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, hashedPassword)
            put(COLUMN_SECURITY_QUESTION, securityQuestion)
            put(COLUMN_SECURITY_ANSWER, securityAnswer)
        }
        return writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_PASSWORD), "$COLUMN_USERNAME = ?", arrayOf(username), null, null, null)

        cursor.use {
            if (it != null && it.moveToFirst()) {
                val storedHashedPassword = it.getString(it.getColumnIndexOrThrow(COLUMN_PASSWORD))
                Log.d("DatabaseHelper", "Stored hashed password: $storedHashedPassword")

                val isPasswordValid = BCrypt.checkpw(password, storedHashedPassword)
                Log.d("DatabaseHelper", "Password verification result: $isPasswordValid")
                return isPasswordValid
            }
        }
        return false
    }

    fun readSecurityAnswer(username: String, securityAnswer: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_SECURITY_ANSWER), "$COLUMN_USERNAME = ? AND $COLUMN_SECURITY_ANSWER = ?", arrayOf(username, securityAnswer), null, null, null)

        cursor.use {
            return it != null && it.count > 0
        }
    }

    fun updatePassword(username: String, newPassword: String) {
        val hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt())
        val values = ContentValues().apply {
            put(COLUMN_PASSWORD, hashedPassword)
        }
        writableDatabase.update(TABLE_NAME, values, "$COLUMN_USERNAME = ?", arrayOf(username))
        Log.d("DatabaseHelper", "Password updated for user: $username")
    }

}
