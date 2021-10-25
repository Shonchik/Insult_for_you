package com.example.abuseyou.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abuse_item")
data class AbuseModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val number: String,
    @ColumnInfo val language: String,
    @ColumnInfo val insult: String,
    @ColumnInfo val created: String,
    @ColumnInfo val shown: String,
    @ColumnInfo val createdby: String,
    @ColumnInfo val active: String,
    @ColumnInfo val comment: String,
)