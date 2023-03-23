package com.test.a4.data

import com.test.a4.data.db.dao.TeamDao
import com.test.a4.data.db.entities.TeamEntity
import com.test.a4.domain.DatabaseRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DatabaseRepositoryImpl(
    private val db : TeamDao
): DatabaseRepository {
    override fun getTeam(): Single<TeamEntity> {
       return db.getTeam()
    }

    override fun getTeams(): Single<List<TeamEntity>> {
        return db.getTeams()
    }

    override fun insertTeam(team: TeamEntity): Completable {
        return db.insertTeam(team)
    }

    override fun removeAll(): Completable {
        return db.removeAll()
    }
}