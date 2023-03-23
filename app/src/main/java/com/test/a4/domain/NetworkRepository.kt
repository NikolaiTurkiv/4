package com.test.a4.domain

import com.test.a4.data.network.response.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Query

interface NetworkRepository {
    fun getTeams(): Single<List<GeneralTeamResponse>>

    fun getClubInfoResponse(
        clubName: String
    ): Single<List<ClubInfoResponse>>

    fun getResults(
        clubName: String
    ): Single<List<ResultsResponse>>

    fun getFixtures(
        clubName: String
    ): Single<List<FixturesResponse>>

    fun getSquad(
        clubName: String
    ): Single<List<SquadResponse>>

    fun getTable(): Single<List<TableInfoResponse>>

    fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String,
    ): Single<SplashResponse>
}