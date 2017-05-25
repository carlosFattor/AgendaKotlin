package com.guildacode.agendakotlin.model

import java.io.Serializable


/**
 * Created by carlos on 24/05/17.
 */

data class Student(
        val id: Long?,
        val email: String,
        val name: String,
        val address: String,
        val phone: String,
        val rating: Double) : Serializable {
    constructor(): this(null, "", "", "", "", 0.0)
}