package com.example.demo_room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String,
    val age: Int)