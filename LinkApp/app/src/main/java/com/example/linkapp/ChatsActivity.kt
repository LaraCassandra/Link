package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.linkapp.fragments.ChatsFragment
import com.example.linkapp.fragments.ContactsFragment
import com.example.linkapp.fragments.ProfileFragment
import com.example.linkapp.model.User
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore

class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        // REFERENCE TO FRAGMENTS
        val chatsFragment = ChatsFragment()
        val contactsFragment = ContactsFragment()
        val profileFragment = ProfileFragment()

        //SET THE DEFAULT FRAGMENT
        //APPLY FRAGMENT TO FRAME
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, chatsFragment)
            commit()
        }

        // ON CHATS CLICK
        val tv_chat = findViewById<TextView>(R.id.tv_chat)
        tv_chat.setOnClickListener {
            //APPLY CHATS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, chatsFragment)
                addToBackStack(null)
                commit()
            }
        }

        // ON PROFILE CLICK
        val tv_profile = findViewById<TextView>(R.id.tv_profile)
        tv_chat.setOnClickListener {
            //APPLY PROFILE FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, profileFragment)
                addToBackStack(null)
                commit()
            }
        }

        // ON CONTACTS CLICK
        val tv_contacts = findViewById<TextView>(R.id.tv_contacts)
        tv_chat.setOnClickListener {
            //APPLY CONTACTS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, contactsFragment)
                addToBackStack(null)
                commit()
            }
        }

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