package com.test.a4.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.a4.data.db.entities.TeamEntity
import com.test.a4.data.network.response.GeneralTeamResponse
import com.test.a4.domain.DatabaseRepository
import com.test.a4.domain.DatabaseUseCase
import com.test.a4.domain.NetworkUseCase
import com.test.a4.domain.PreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase,
    private val databaseRepository: DatabaseUseCase,
) : ViewModel() {

    private val _teamsLD = MutableLiveData<List<GeneralTeamResponse>>()
    val teamsLD: LiveData<List<GeneralTeamResponse>>
        get() = _teamsLD

    fun getTeamsDb(){
        databaseRepository.teams
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("getTeamsDb", it.toString())

            },{})
    }

    fun removeAll(){
        databaseRepository.removeAll()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("removeAll", "SUCCESS")
            }, {
                Log.d("removeAll", it.message.toString())
            })
    }
    fun saveName(name: String) {
        databaseRepository.saveTeamName(TeamEntity(name))
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("saveName", "SUCCESS")
            }, {
                Log.d("saveName", it.message.toString())
            })
    }

    fun getTeams() {
        networkUseCase.getTeams()
            .subscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _teamsLD.postValue(it)
            }, {
                Log.d("getTeams", it.message.toString())
            })
    }

    fun prepareTeamName(name: String): String {
        val prepName =
            if (name.contains(' '))
                name.replace(' ', '_')
            else
                name

        return prepName.lowercase()
    }

}