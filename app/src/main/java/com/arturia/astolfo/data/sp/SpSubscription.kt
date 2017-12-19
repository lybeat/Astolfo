package com.arturia.astolfo.data.sp

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.content.SharedPreferencesCompat

/**
 * Author: Arturia
 * Date: 2017/12/19
 */
class SpSubscription {

    companion object {

        fun setSubscription(context: Context, subscription: Boolean) {
            val sp: SharedPreferences = context.getSharedPreferences("sp_version", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sp.edit()
            editor.putBoolean("key_subscription", subscription)
            SharedPreferencesCompat.EditorCompat.getInstance().apply(editor)
        }

        fun getSubscription(context: Context): Boolean {
            val sp: SharedPreferences = context.getSharedPreferences("sp_version", Context.MODE_PRIVATE)
            return sp.getBoolean("key_subscription", true)
        }
    }
}