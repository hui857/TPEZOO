package com.example.zoo.model.remote

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


class ZooAPIClient {

    private val BASE_URL = "https://data.taipei/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getClient(): Retrofit {
        return retrofit
    }
}