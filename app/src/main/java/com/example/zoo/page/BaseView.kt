package com.example.zoo.page

interface BaseView<T>  {
    fun setPresenter(presenter: T)
}