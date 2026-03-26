package com.example.iqtest.domain.model

data class LeaderboardUser(
    val userId: String,
    val displayName: String,
    val topIq: Int,
    val testsTaken: Int
)
