package com.example.linkapp.model

import java.util.*

object MessageType {
    const val TEXT = "TEXT"
}

interface Message {
    val time: Date
    val senderId: String
    val type: String
}