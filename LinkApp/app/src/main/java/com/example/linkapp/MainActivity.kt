package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.Adapters.ContactAdapter
import com.example.linkapp.model.ContactItem
import kotlinx.android.synthetic.main.fragment_contacts.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LOGIN BUTTON CLICK
        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener{
            // DIRECT TO LOGIN PAGE
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // SIGN UP BUTTON CLICK
        val btn_register = findViewById<Button>(R.id.btn_register)
        btn_register.setOnClickListener {
            // DIRECT TO REGISTER PAGE
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        //  TODO: FIX CONTACTS DISPLAY
//        val contactList = mutableListOf(
//            ContactItem(1, "John Doe", "johndoe@gmail.com"),
//            ContactItem(2, "Hannah Smith", "hannahsmith@gmail.com"),
//        )
//
//        rv_container.adapter = ContactAdapter(contactList, this)
//        rv_container.layoutManager = LinearLayoutManager(this)
//        rv_container.setHasFixedSize(true)

    }
}