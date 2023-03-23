package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a4.domain.NetworkUseCase
import com.test.a4.ui.adapters.Delegates
import com.test.a4.ui.adapters.entity.LeagueTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase,
) : ViewModel(){

    private val _leagueLD = MutableLiveData<List<Delegates>>()
    val leagueLD: LiveData<List<Delegates>>
        get() = _leagueLD


    fun getTable(){
        networkUseCase.getTable()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                val list = mutableListOf<Delegates>(LeagueTitle())
                list.addAll(it)
                _leagueLD.postValue(list)
                Log.d("getTable",it.toString())
            },{
                Log.d("getTable",it.message.toString())
            })
    }
}
