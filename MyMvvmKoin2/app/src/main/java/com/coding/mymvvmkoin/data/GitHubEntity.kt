package com.coding.mymvvmkoin.data.openHab


import androidx.annotation.NonNull
import androidx.room.*



@Entity(tableName = "github_table", indices = [Index(value = arrayOf("entityId"), unique = true)])
data class GitHubEntity(@PrimaryKey
                               @NonNull
                               @ColumnInfo(name = "entityId") val entityId: String,
                               @ColumnInfo(name = "name") val name: String,
                               @ColumnInfo(name = "image") val image: String)

