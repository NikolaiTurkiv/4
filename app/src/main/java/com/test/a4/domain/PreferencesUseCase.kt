package com.test.a4.domain

class PreferencesUseCase(
    private val preferencesRepository: PreferencesRepository
) {

    val uniqID = preferencesRepository.id

    fun saveId(id:String){
        preferencesRepository.saveId(id)
    }
}
