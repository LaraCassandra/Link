package com.example.linkapp.model

 data class ChatChannel(
     val userIds: MutableList<String>
 ) { constructor() : this(mutableListOf())
}