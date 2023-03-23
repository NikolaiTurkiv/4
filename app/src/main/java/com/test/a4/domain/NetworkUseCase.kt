package com.test.a4.domain

import com.test.a4.data.network.response.*
import io.reactivex.rxjava3.core.Single

class NetworkUseCase(
    private val networkRepository: NetworkRepository
) {
    fun getTeams(): Single<List<GeneralTeamResponse>>{
        return networkRepository.getTeams()
    }

    fun getClubInfoResponse(
        clubName: String
    ): Single<List<ClubInfoResponse>>{
        return networkRepository.getClubInfoResponse(clubName)
    }

    fun getResults(
        clubName: String
    ): Single<List<ResultsResponse>>{
        return networkRepository.getResults(clubName)
    }

    fun getFixtures(
        clubName: String
    ): Single<List<FixturesResponse>>{
        return networkRepository.getFixtures(clubName)
    }

    fun getSquad(
        clubName: String
    ): Single<List<SquadResponse>>{
        return networkRepository.getSquad(clubName)
    }

    fun getTable(): Single<List<TableInfoResponse>>{
        return networkRepository.getTable()
    }

    fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String,
    ): Single<SplashResponse>{
        return networkRepository.fetchPhoneStatus(phoneName, locale, id)
    }
}