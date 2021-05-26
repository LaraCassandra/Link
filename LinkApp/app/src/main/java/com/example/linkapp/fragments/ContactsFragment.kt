package com.example.linkapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.adapters.ContactAdapter
import com.example.linkapp.R
import com.example.linkapp.model.ContactItem
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

            val contactList = mutableListOf(
                ContactItem(1, "John Doe", "johndoe@gmail.com"),
                ContactItem(2, "Hansen Li", "hansenli@gmail.com")
            )

            adapter = ContactAdapter(contactList, this)
            setHasFixedSize(true)
        }

    }

}