package com.test.a4.data

import com.test.a4.data.network.NetworkApi
import com.test.a4.data.network.PhoneInfoRequest
import com.test.a4.data.network.response.*
import com.test.a4.domain.NetworkRepository
import io.reactivex.rxjava3.core.Single

class NetworkRepositoryImpl(
    private val api: NetworkApi
) : NetworkRepository {
    override fun getTeams(): Single<List<GeneralTeamResponse>> {
        return api.getTeams()
    }

    override fun getClubInfoResponse(clubName: String): Single<List<ClubInfoResponse>> {
        return api.getClubInfoResponse(clubName)
    }

    override fun getResults(clubName: String): Single<List<ResultsResponse>> {
        return api.getResults(clubName)
    }

    override fun getFixtures(clubName: String): Single<List<FixturesResponse>> {
        return api.getFixtures(clubName)
    }

    override fun getSquad(clubName: String): Single<List<SquadResponse>> {
        return api.getSquad(clubName)
    }

    override fun getTable(): Single<List<TableInfoResponse>> {
        return api.getTable()
    }

    override fun fetchPhoneStatus(
        phoneName: String,
        locale: String,
        id: String
    ): Single<SplashResponse> {
        return api.fetchPhoneStatus(
//            phoneName, locale, id
            PhoneInfoRequest(phoneName, locale, id)

        )
//            PhoneInfoRequest(phoneName, locale, id)

    }
}
