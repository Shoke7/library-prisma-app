package com.sigma.library.sigmalibrary.model

data class User(
    val name: String,
    val firstName: String,
    val memberSince: String,
    val memberTill: String,
    val memberSinceTimestamp: Long,
    val memberTillTimestamp: Long,
    val gender: String,
)
