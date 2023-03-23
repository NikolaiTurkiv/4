package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a4.data.network.response.ClubInfoResponse
import com.test.a4.domain.DatabaseRepository
import com.test.a4.domain.DatabaseUseCase
import com.test.a4.domain.NetworkUseCase
import com.test.a4.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase,
    private val database: DatabaseUseCase,
) : ViewModel() {

    private val _nameLD = MutableLiveData<String>()
    val nameLD: LiveData<String>
        get() = _nameLD

    init {
        database.teamName
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       _nameLD.postValue(it.teamName)
            },{
                Log.d("DB",it.message.toString())
            })
    }

    private val _clubInfoLD = MutableLiveData<List<ClubInfoResponse>>()
    val clubInfoLD: LiveData<List<ClubInfoResponse>>
        get() = _clubInfoLD

    fun getClubInfo(name: String){
        networkUseCase.getClubInfoResponse(name)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                _clubInfoLD.postValue(it)
            },{
                Log.d("getClubInfo",it.message.toString())
            })
    }

}