package com.example.linkapp.model

import com.example.linkapp.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class TextMessageItem(
    val message: TextMessage?,
    val context: android.content.Context
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getLayout() = R.layout.item_text_message
    }