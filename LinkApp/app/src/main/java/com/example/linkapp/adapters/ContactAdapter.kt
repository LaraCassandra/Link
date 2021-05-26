package com.example.linkapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.linkapp.R
import com.example.linkapp.model.ContactItem
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    private val itemList: List<ContactItem>,
    private val activity: RecyclerView
        ) : RecyclerView.Adapter<ContactAdapter.ListViewHolder>() {

        // INNER CLASS TO HANDLE ITEM HOLDER
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            // DEFINE ELEMENTS THAT WE INTERACT WITH IN THE ITEM_CONTACT.XML
            val nameView: TextView = itemView.tv_name
            val emailView: TextView = itemView.tv_email

        }

    // INFLATE LISTVIEWHOLDER (EACH ITEM) TO RECYCLERVIEW CONTAINER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate( R.layout.item_contact, parent, false)

        return ListViewHolder(itemView)
    }

    // SAY WHAT ITEMS IN ITEM_COUNTS SHOULD BE UPDATED IN CONTACTS DATA
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = itemList[position]

        // SET THE TEXT OF THE ITEM
        holder.nameView.text = currentItem.name
        holder.emailView.text = currentItem.email
    }

    // RETURN HOW MANY ITEMS ARE IN THE LIST
    override fun getItemCount(): Int {
        return itemList.size
    }
}