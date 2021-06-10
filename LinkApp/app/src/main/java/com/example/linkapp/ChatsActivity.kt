package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import com.example.linkapp.fragments.ChatsFragment
import com.example.linkapp.fragments.ContactsFragment
import com.example.linkapp.fragments.ProfileFragment
import com.example.linkapp.model.User
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_profile.*

class ChatsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        // SETUP TOOLBAR
        setSupportActionBar((findViewById(R.id.tb_toolbar)))

        // REFERENCE TO FRAGMENTS
        val chatsFragment = ChatsFragment()
        val contactsFragment = ContactsFragment()
        val profileFragment = ProfileFragment()


        //SET DEFAULT FRAGMENT
        //APPLY FRAGMENT TO FRAME
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, chatsFragment)
            commit()
        }

        // ON PROFILE CLICK
        val ib_profile = findViewById<ImageButton>(R.id.ib_profile)
        ib_profile.setOnClickListener {
            //APPLY PROFILE FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, profileFragment)
                addToBackStack(null)
                commit()
            }
        }
//
        // ON CONTACTS CLICK
        val ib_contacts = findViewById<ImageButton>(R.id.ib_contacts)
        ib_contacts.setOnClickListener {
            //APPLY CONTACTS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, contactsFragment)
                addToBackStack(null)
                commit()
            }
        }

        // ON CHATS CLICK
        val ib_chats = findViewById<ImageButton>(R.id.ib_chats)
        ib_chats.setOnClickListener {
            //APPLY CHATS FRAGMENT TO FRAME
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, chatsFragment)
                addToBackStack(null)
                commit()
            }
        }

//
//        val uid = intent.getStringExtra(Constants.LOGGED_IN_ID)
//
//        if(uid != null){
//            getUserInfoById(this, uid)
//        }
//        else {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                FirebaseAuth.getInstance().signOut()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun openChat() {
        val intent = Intent(this, MessageActivity::class.java)

    }


}
