package com.test.a4.domain

class PreferencesUseCase(
    private val preferencesRepository: PreferencesRepository
) {

    val name = preferencesRepository.actualName

    fun saveName(name:String){
        preferencesRepository.saveActualName(name)
    }
}
