package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.a4.domain.DatabaseUseCase
import com.test.a4.domain.NetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase,
    private val databaseRepository: DatabaseUseCase,
) : ViewModel() {

    fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String,
    ) {
        networkUseCase.fetchPhoneStatus(phoneName, locale, id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("fetchPhoneStatus", it.url)
            }, {
                Log.d("fetchPhoneStatus", it.message.toString())
            })
    }
}