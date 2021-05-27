package com.example.linkapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.linkapp.ChatsActivity
import com.example.linkapp.MessageActivity
import com.example.linkapp.R
import com.example.linkapp.RegisterActivity
import com.example.linkapp.model.ChatItem
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatAdapter(
    private val chatItemList: List<ChatItem>,
    private val fragment: Fragment
    ) : RecyclerView.Adapter<ChatAdapter.ChatListViewHolder>() {

    // INNER CLASS TO HANDLE ITEM HOLDER
    inner class ChatListViewHolder(chatItemView: View) : RecyclerView.ViewHolder(chatItemView){
        //DEFINE ELEMENTS THAT WE INTERACT WITH IN ITEM_CHAT
        val nameView: TextView = chatItemView.tv_contactName
        val messageView: TextView = chatItemView.tv_messagePreview
        val chatButton: LinearLayout = chatItemView.item_chat
    }

    // INFLATE LISTVIEW HOLDER (EACH ITEM) TO CONTAINER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val chatItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)

        return ChatListViewHolder(chatItemView)
    }

    // TELL WHAT ITEMS IN ITEM_CHAT SHOULD BE UPDATED TO ITEMS DATA
    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        val currentItem = chatItemList[position]
        holder.nameView.text = currentItem.contactName
        holder.messageView.text = currentItem.message

        // ADD ON CLICK LISTENER TO HOLDER
        holder.chatButton.setOnClickListener{
        }
    }

    // TELL HOW MANY ITEMS ARE IN THE LST
    override fun getItemCount(): Int {
        return chatItemList.size
    }
}