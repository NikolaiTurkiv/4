package com.test.a4.domain

import com.test.a4.data.db.entities.TeamEntity
import io.reactivex.rxjava3.core.Completable

class DatabaseUseCase(
    private val database: DatabaseRepository
) {
    val teamName = database.getTeam()

    val teams = database.getTeams()

    fun saveTeamName(teamEntity: TeamEntity): Completable{
        return database.insertTeam(teamEntity)
    }
    fun removeAll(): Completable{
        return database.removeAll()
    }
}