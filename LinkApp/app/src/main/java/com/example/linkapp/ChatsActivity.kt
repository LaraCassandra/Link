package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.linkapp.fragments.ChatsFragment
import com.example.linkapp.fragments.ContactsFragment
import com.example.linkapp.fragments.ProfileFragment
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

        // ON PROFILE CLICK
        val tv_profile = findViewById<TextView>(R.id.tv_profile)
        tv_profile.setOnClickListener {
            //APPLY PROFILE FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, profileFragment)
                addToBackStack(null)
                commit()
            }
        }
//
        // ON CONTACTS CLICK
        val tv_contacts = findViewById<TextView>(R.id.tv_contacts)
        tv_contacts.setOnClickListener {
            //APPLY CONTACTS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, contactsFragment)
                addToBackStack(null)
                commit()
            }
        }

        // ON CHATS CLICK
        val tv_chat = findViewById<TextView>(R.id.tv_chats)
        tv_chat.setOnClickListener {
            //APPLY CHATS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, chatsFragment)
                addToBackStack(null)
                commit()
            }
        }

//
        val uid = intent.getStringExtra(Constants.LOGGED_IN_ID)

        if(uid != null){
            Firestore().getUserInfoById(this, uid)
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))

            finish()
        }
    }


}