package com.sigma.library.sigmalibrary.model

data class Borrowed(
    val borrower:String,
    val book:String,
    val borrowedFrom: String,
    val borrowedTo:String,
    val borrowedFromTimestamp: Long,
    val borrowedToTimestamp:Long
)
