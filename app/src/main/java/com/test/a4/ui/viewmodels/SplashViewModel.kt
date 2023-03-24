package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a4.data.network.response.SplashResponse
import com.test.a4.domain.NetworkUseCase
import com.test.a4.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase,
    private val preferencesUseCase: PreferencesUseCase,
) : ViewModel() {

    val id = preferencesUseCase.uniqID

    private val _response = MutableLiveData<SplashResponse>()
    val response: LiveData<SplashResponse>
    get() = _response

    fun saveId(id:String){
        preferencesUseCase.saveId(id)
    }

    fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String,
    ) {
        networkUseCase.fetchPhoneStatus(phoneName, locale, id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                _response.postValue(it)
            }, {
                Log.d("fetchPhoneStatus", it.message.toString())
            })
    }
}