package com.test.a4.data.network

 import com.test.a4.data.network.response.*
import io.reactivex.rxjava3.core.Single
 import retrofit2.http.Body
 import retrofit2.http.GET
 import retrofit2.http.POST
 import retrofit2.http.Path
 import retrofit2.http.Query

interface NetworkApi {

    @GET("4/teams.json")
    fun getTeams(): Single<List<GeneralTeamResponse>>

    @GET("4/{clubName}_club_info.json")
    fun getClubInfoResponse(
        @Path("clubName") clubName: String
    ): Single<List<ClubInfoResponse>>

    @GET("4/{clubName}_results.json")
    fun getResults(
        @Path("clubName") clubName: String
    ): Single<List<ResultsResponse>>

    @GET("4/{clubName}_fixtures.json")
    fun getFixtures(
        @Path("clubName") clubName: String
    ): Single<List<FixturesResponse>>

    @GET("4/{clubName}_squad.json")
    fun getSquad(
        @Path("clubName") clubName: String
    ): Single<List<SquadResponse>>

    @GET("4/prem_table.json")
    fun getTable(): Single<List<TableInfoResponse>>

    @POST("splash.php")
    fun fetchPhoneStatus(
       @Body request: PhoneInfoRequest
    ): Single<SplashResponse>

}
