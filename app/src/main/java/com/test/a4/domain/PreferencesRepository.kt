package com.test.a4.domain

interface PreferencesRepository {

    val actualName: String
    fun saveActualName(name:String)

}