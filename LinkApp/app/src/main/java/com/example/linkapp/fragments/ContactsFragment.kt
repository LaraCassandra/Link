package com.example.linkapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.ChatsActivity
import com.example.linkapp.MessageActivity
import com.example.linkapp.R
import com.example.linkapp.model.ContactItem
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.FirestoreUtil
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(){

    private lateinit var userListnerRegistration: ListenerRegistration
    private var shouldInitRecyclerView = true

    private lateinit var contactSection: Section

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userListnerRegistration = FirestoreUtil.addUserListener(this.activity!!, this::updateRecyclerView)

        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        FirestoreUtil.removeListener(userListnerRegistration)
        shouldInitRecyclerView = true
    }

    private fun updateRecyclerView(items: List<Item>){

        fun init(){
            rv_container.apply {
                layoutManager = LinearLayoutManager(this@ContactsFragment.context)
                adapter = GroupAdapter<ViewHolder>().apply {
                    contactSection = Section(items)
                    add(contactSection)
                    setOnItemClickListener(onItemClick)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItems() = contactSection.update(items)

        if(shouldInitRecyclerView)
            init()
        else
            updateItems()

    }

    private val onItemClick = OnItemClickListener { item, view ->
        if (item is ContactItem) {
            val intent = Intent(activity as ChatsActivity, MessageActivity::class.java)
            intent.putExtra(Constants.USERNAME, item.person?.displayName)
            intent.putExtra(Constants.USERID, item.userId)
            startActivity(intent)
        }
    }


//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//        rv_container.apply {
//            layoutManager = LinearLayoutManager(activity)
//
//            val db = FirebaseFirestore.getInstance()
//            val contactLit = db.collection("users").get()
//                .addOnSuccessListener { document ->
//                    if(document != null){
//
//                        val contact = document.documents.toList()
//                        Log.w("This is the documents", "$contact")
//
//                        adapter = ContactAdapter(contact, this)
//                        setHasFixedSize(true)
//
//                    }
//                }
//        }
//
//    }

}