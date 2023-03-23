package com.test.a4.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.a4.data.db.entities.TeamEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TeamDao {
    @Query("SELECT * FROM team_entity")
    fun getTeam(): Single<TeamEntity>

    @Query("SELECT * FROM team_entity")
    fun getTeams(): Single<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: TeamEntity): Completable

    @Query("DELETE FROM team_entity")
    fun removeAll():Completable
}