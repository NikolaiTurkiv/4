package com.test.a4.domain

interface PreferencesRepository {

    val id: String
    fun saveId(id:String)

}