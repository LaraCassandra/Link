package com.example.linkapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.Adapters.ContactAdapter
import com.example.linkapp.R
import com.example.linkapp.model.ContactItem
import com.example.linkapp.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        rv_container.apply {
            layoutManager = LinearLayoutManager(activity)

            val db = FirebaseFirestore.getInstance()
            val contactLit = db.collection("users").get()
                .addOnSuccessListener { document ->
                    if(document != null){

                        val contact = document.documents.toList()
                        Log.w("This is the documents", "$contact")

                        adapter = ContactAdapter(contact, this)
                        setHasFixedSize(true)

                    }
                }
        }

    }

}