package com.test.a4.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "team_entity")
data class TeamEntity(
    @PrimaryKey
    val teamName:String
)