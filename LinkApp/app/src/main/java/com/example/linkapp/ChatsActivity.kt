package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.linkapp.model.User
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore

class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        val userId = intent.getStringExtra(Constants.LOGGED_IN_ID)

        if(userId != null){
            Firestore().getUserInfoById(this, userId)
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun setUserInfo(user: User){
        title = user.name
    }

}