package com.example.zoo.utils

import android.widget.TextView
import com.example.zoo.R


fun TextView.setMemo(memo: String?) {
    text = memo ?: this.context.getString(R.string.msg_all_year)
}