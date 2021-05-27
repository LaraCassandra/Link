package com.example.linkapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linkapp.R
import com.example.linkapp.adapters.ChatAdapter
import com.example.linkapp.model.ChatItem
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)

        val chatList = mutableListOf(
            ChatItem(1, 2, "John", "Hey how are you")
        )

        //SET UP RECYCLERVIEW ADAPTER
        rv_container.adapter = ChatAdapter(chatList, this)
        rv_container.setHasFixedSize(true)

    }

}