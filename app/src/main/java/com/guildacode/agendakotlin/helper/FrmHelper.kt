package com.guildacode.agendakotlin.helper

import com.guildacode.agendakotlin.FormActivity
import com.guildacode.agendakotlin.model.Student
import kotlinx.android.synthetic.main.activity_form.*

/**
 * Created by carlos on 24/05/17.
 */
class FrmHelper {
    var student: Student
    val _frm: FormActivity

    constructor(frm: FormActivity) {
        _frm = frm
        student = Student(
                null,
                frm.frm_email.text.toString(),
                frm.frm_name.text.toString(),
                frm.frm_address.text.toString(),
                frm.frm_phone.text.toString(),
                frm.frm_rating.progress.toDouble())

    }

    fun fillViewStudent(_student: Student): Unit {
        _frm.frm_email.setText(_student.email)
        _frm.frm_name.setText(_student.name)
        _frm.frm_address.setText(_student.address)
        _frm.frm_phone.setText(_student.phone)
        _frm.frm_rating.progress = _student.rating.toInt()
        student = _student
    }

    fun updateStudent(id: Long): Student {
        student = Student(
                id,
                _frm.frm_email.text.toString(),
                _frm.frm_name.text.toString(),
                _frm.frm_address.text.toString(),
                _frm.frm_phone.text.toString(),
                _frm.frm_rating.progress.toDouble())
        return student
    }
}