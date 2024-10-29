package com.appilary.evisit.database

import com.appilary.evisit.database.models.UserData

interface UserRepository {
    suspend fun addData(user: UserData)
    suspend fun getAllData(): List<UserData>
    suspend fun deleteData(user: UserData)
}