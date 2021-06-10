package com.example.linkapp.model

import android.content.Context
import com.example.linkapp.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_contact.*

data class ContactItem(
    val person: User?,
    val userId: String,
    private val context: Context
) : Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.tv_name.text = person?.displayName
        viewHolder.tv_email.text = person?.email
    }

    override fun getLayout() = R.layout.item_contact
    }