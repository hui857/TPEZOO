package com.example.zoo.page

interface BaseToolbarImageView<T> :BaseView<T>{
    fun showHeaderPic(picUrl: String)
    fun showTitle(chName: String)
}