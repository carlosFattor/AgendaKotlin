package com.guildacode.agendakotlin.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.guildacode.agendakotlin.model.Student
import java.util.*

/**
 * Created by carlos on 24/05/17.
 */
class StudentDAO(context: Context) : SQLiteOpenHelper(context, "Students", null, 1) {
    val db: SQLiteDatabase = writableDatabase

    companion object {
        val table: String = "Students"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE $table (id INTEGER PRIMARY KEY, " +
                "email TEXT NOT NULL, name TEXT NOT NULL, address TEXT, phone TEXT, rating REAL);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $table"
        db?.execSQL(sql)
        onCreate(db)
    }

    fun insertStudent(student: Student) {

        val data = parseStudentToContentValue(student)

        db.insert(table, null, data)
    }

    fun listStudents(): ArrayList<Student> {
        var students = arrayListOf<Student>()

        val sql = "SELECT * FROM $table"
        val cursor = db.rawQuery(sql, null)

        while (cursor.moveToNext()){
            val student = Student(cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getDouble(cursor.getColumnIndex("rating")))

            students.add(student)
        }
        cursor.close()

        return students
    }

    fun delete(studentId: Long) {
        db.delete(table, "id = ?", arrayOf(studentId.toString()))
    }

    fun updateStudent(student: Student) {
        val data = parseStudentToContentValue(student)
        Log.i("UPDATE=> ", data.toString())
        Log.i("ID=> ", student.id?.toString())
        db.update(table, data, "id = ?", arrayOf(student.id?.toString()))
    }

    fun parseStudentToContentValue(student: Student): ContentValues {
        val data = ContentValues()
        data.put("email", student.email)
        data.put("name", student.name)
        data.put("address", student.address)
        data.put("phone", student.phone)
        data.put("rating", student.rating)

        return data
    }
}