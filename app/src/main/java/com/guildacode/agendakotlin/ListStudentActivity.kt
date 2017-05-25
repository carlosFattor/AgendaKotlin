package com.guildacode.agendakotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.guildacode.agendakotlin.dao.StudentDAO
import com.guildacode.agendakotlin.model.Student
import kotlinx.android.synthetic.main.activity_list_students.*

class ListStudentActivity : AppCompatActivity() {

    var listStudents = arrayListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)

        btn_new_user.setOnClickListener {
            val go2Form = Intent(this, FormActivity::class.java)
            startActivity(go2Form)
        }

        registerForContextMenu(lst_students)

        // fast click on item list of ListView
        lst_students.setOnItemClickListener { list, item, position, id ->
            val student = listStudents[position]
            val go2form = Intent(this, FormActivity::class.java)
            go2form.putExtra("student", student)
            startActivity(go2form)
         }
    }

    override fun onResume() {
        super.onResume()
        loadStudents()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val delete = menu?.add("Delete")

        delete?.setOnMenuItemClickListener {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            val student = listStudents[info.position]
            StudentDAO(this).delete(student.id!!)
            loadStudents()
            Toast.makeText(this, "Student ${student.name} deleted", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun loadStudents() {
        val dao = StudentDAO(this)
        listStudents = dao.listStudents()
        dao.close()
        val adapter = ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, listStudents)

        lst_students.adapter = adapter
    }
}
