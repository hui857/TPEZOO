package com.example.zoo.model.remote.apiservice

import io.reactivex.Observable
import com.example.zoo.model.remote.data.AreaResponse
import com.example.zoo.model.remote.data.PlantResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooApiService {
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getAreas(): Observable<AreaResponse>

    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlants(
        @Query("q") q: String = ""
    ): Observable<PlantResponse>
}