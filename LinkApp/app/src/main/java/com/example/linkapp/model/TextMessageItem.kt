package com.example.linkapp.model

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.example.linkapp.R
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_text_message.*
import java.text.SimpleDateFormat

class TextMessageItem(
    val message: TextMessage,
    val context: Context
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.tv_message.text = message.text
        setTimeText(viewHolder)
        setMessageRootGravity(viewHolder)
    }

    private fun setTimeText(viewHolder: ViewHolder){
        val dateFormat = SimpleDateFormat
            .getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)
        viewHolder.tv_date.text = dateFormat.format(message.time)
    }

    private fun setMessageRootGravity(viewHolder: ViewHolder){
        if (message.senderId == FirebaseAuth.getInstance().currentUser?.uid){
            viewHolder.rl_message_root.apply{
                setBackgroundResource(R.drawable.message_blue_colour)
                val lParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.END)
                this.layoutParams = lParams
            }
        }
        else {
            viewHolder.rl_message_root.apply {
                setBackgroundResource(R.drawable.message_light_blue_colour)
                val lParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.START)
                this.layoutParams = lParams
            }

        }
    }

    override fun getLayout() = R.layout.item_text_message
    }