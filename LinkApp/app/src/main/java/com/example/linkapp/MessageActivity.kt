package com.example.linkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.model.MessageType
import com.example.linkapp.model.TextMessage
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.activity_message.*
import java.util.*

class MessageActivity : AppCompatActivity() {

    private lateinit var messagesListenerRegistration: ListenerRegistration
    private var shouldInitRecyclerView = true
    private lateinit var messageSection: Section

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(Constants.USERNAME)

        Firestore.getCurrentUser {
            val currentUser = it
        }

        val otherUserId = intent.getStringExtra(Constants.USERID)

            Firestore.getOrCreateChatChannel(otherUserId.toString()){ channelId ->
                val currentChannelId = channelId
                messagesListenerRegistration =
                    Firestore.addChatMessagesListener(channelId, this, this::updateRecyclerView)

                btn_send.setOnClickListener {
                    val messageToSend =
                        TextMessage(et_message.text.toString(), Calendar.getInstance().time,
                            FirebaseAuth.getInstance().currentUser!!.uid, MessageType.TEXT)
                    et_message.setText("")
                    Firestore.sendMessage(messageToSend, channelId)
                }
            }

    }

    private fun updateRecyclerView(messages: List<Item>){

        fun init(){
            rv_container.apply{
                layoutManager = LinearLayoutManager(this@MessageActivity)
                adapter = GroupAdapter<ViewHolder>().apply{
                    messageSection = Section(messages)
                    this.add(messageSection)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItems() = messageSection.update(messages)

        if(shouldInitRecyclerView)
            init()
        else
            updateItems()
    }
}