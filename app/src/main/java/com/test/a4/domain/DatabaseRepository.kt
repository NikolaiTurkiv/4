package com.test.a4.domain

import com.test.a4.data.db.entities.TeamEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface DatabaseRepository {
    fun getTeam(): Single<TeamEntity>
    fun getTeams(): Single<List<TeamEntity>>
    fun insertTeam(team: TeamEntity): Completable
    fun removeAll():Completable
}