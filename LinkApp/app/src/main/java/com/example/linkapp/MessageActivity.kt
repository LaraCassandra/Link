package com.example.linkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.FirestoreUtil
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {

    private lateinit var messagesListenerRegistration: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = intent.getStringExtra(Constants.USERNAME)

        val otherUserId = intent.getStringExtra(Constants.USERID)

        if (otherUserId != null) {
            FirestoreUtil.getOrCreateChatChannel(otherUserId){ channelId ->
                messagesListenerRegistration =
                    FirestoreUtil.addChatMessagesListener(channelId, this, this::onMessageChanged)
            }
        }

    }

    private fun onMessageChanged(message: List<Item>){
        Log.e("onMessagesChanged", "Running")
    }
}