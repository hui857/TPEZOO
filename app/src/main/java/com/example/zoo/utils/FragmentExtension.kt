package com.example.zoo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.addToActivity(
    fragmentManager: FragmentManager, frameId: Int) {
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, this)
    transaction.commit()
}

