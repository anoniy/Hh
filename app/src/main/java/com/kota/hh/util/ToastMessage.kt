package com.kota.hh.util

import android.content.Context
import android.widget.Toast

class ToastMessage{

    companion object{
        fun show(context: Context, messageId: Int){
            Toast.makeText(context, messageId, Toast.LENGTH_LONG).show()
        }
        fun show(context: Context, message: String){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

}