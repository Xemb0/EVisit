package com.appilary.evisit.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String
)