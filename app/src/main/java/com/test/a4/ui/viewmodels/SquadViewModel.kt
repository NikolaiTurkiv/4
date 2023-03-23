package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a4.data.network.response.GeneralTeamResponse
import com.test.a4.data.network.response.SquadResponse
import com.test.a4.domain.DatabaseUseCase
import com.test.a4.domain.NetworkUseCase
import com.test.a4.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SquadViewModel@Inject constructor(
    private val networkUseCase: NetworkUseCase,
    private val database: DatabaseUseCase,
) : ViewModel()  {

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

    private val _squadLD = MutableLiveData<List<SquadResponse>>()
    val squadLD: LiveData<List<SquadResponse>>
        get() = _squadLD


    fun getSquad(name: String){
        networkUseCase.getSquad(name)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                _squadLD.postValue(it)
                Log.d("getSquad",it.toString())

            },{
                Log.d("getSquad",it.message.toString())
            })
    }
}