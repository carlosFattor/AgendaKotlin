package com.guildacode.agendakotlin


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.guildacode.agendakotlin.dao.StudentDAO
import com.guildacode.agendakotlin.helper.FrmHelper
import com.guildacode.agendakotlin.model.Student
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    var student: Student? = Student()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        student = intent.getSerializableExtra("student") as Student?
        if(student?.id != null){
            Log.i("FILL_STUDENT==> ", student.toString())
            FrmHelper(this).fillViewStudent(student!!)
        }
    }

    //Adding menu on TopBarMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_form, menu)

        return super.onCreateOptionsMenu(menu)
    }

    //actions about TopBarMenu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_form_ok -> {
                createUpdateStudent()
                Toast.makeText(this, "Student ${student?.name} saved", Toast.LENGTH_SHORT).show()
                finish()
            }
            else -> Toast.makeText(this, "Error trying save student", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createUpdateStudent() {
        val studentDao = StudentDAO(this)

        if (student?.id == null) {
            student = FrmHelper(this).student
            studentDao.insertStudent(student!!)
        } else {
            student = FrmHelper(this).updateStudent(student?.id!!)
            studentDao.updateStudent(student!!)
        }
        studentDao.close()
    }
}


