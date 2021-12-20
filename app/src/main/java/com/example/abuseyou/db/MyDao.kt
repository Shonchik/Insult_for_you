package com.example.abuseyou.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abuseyou.db.model.AbuseModel

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsult(items: AbuseModel)

    @Query("SELECT * FROM abuse_item")
    suspend fun getAllInsults(): List<AbuseModel>

    @Query("SELECT * FROM abuse_item WHERE insult LIKE :item")
    suspend fun searchAllInsults(item: String): List<AbuseModel>

    @Query("DELETE FROM abuse_item WHERE id == :item")
    suspend fun removeInsult(item: Int)

}
