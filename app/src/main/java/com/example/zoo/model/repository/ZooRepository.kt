package com.example.zoo.model.repository

import android.content.Context
import android.util.Log
import com.example.zoo.model.remote.ZooAPIClient
import com.example.zoo.model.remote.apiservice.ZooApiService
import com.example.zoo.model.remote.data.AreaInfo
import com.example.zoo.model.remote.data.PlantInfo
import io.reactivex.Observable
import java.io.BufferedReader
import java.io.InputStreamReader

class ZooRepository {

    companion object {
        @JvmStatic
        fun newInstance() = ZooRepository()
    }

    private val service: ZooApiService by lazy {
        ZooAPIClient().getClient().create(ZooApiService::class.java)
    }

    fun getAreas(context: Context): Observable<List<AreaInfo>> {
        return service.getAreas()
            .map { it.result.results }
            .onErrorResumeNext(getAreasFromLocal(context))
    }

    fun getPlants(context: Context, q: String): Observable<List<PlantInfo>> {
        return service.getPlants(q)
            .map { it.result.results }
            .onErrorResumeNext(getPlantsFromLocal(context, q))
    }

    private fun getAreasFromLocal(context: Context): Observable<List<AreaInfo>> {
        return Observable.create { emitter ->
            val results = mutableListOf<AreaInfo>()
            val reader = BufferedReader(InputStreamReader(context.assets.open("area.csv")))
            var line: String?
            var i = 0
            var keyList: List<String> = arrayListOf()

            while (reader.readLine().also { line = it } != null) {
                val row: List<String> = line!!.split(",")
                if (row[0].startsWith("E_")) {
                    keyList = row
                    continue
                }

                if (keyList.isEmpty() || row.size != keyList.size) {
                    continue
                }

                val id = i
                val category = row[keyList.indexOf("E_Category")]
                val geo = row[keyList.indexOf("E_Geo")]
                val info = row[keyList.indexOf("E_Info")]
                val memo = row[keyList.indexOf("E_Memo")]
                val name = row[keyList.indexOf("E_Name")]
                val picUrl = row[keyList.indexOf("E_Pic_URL")]
                val url = row[keyList.indexOf("E_URL")]
                val no = row[keyList.indexOf("E_no")]

                results.add(AreaInfo(id, category, geo, info, memo, name, picUrl, url, no))
                i++
            }
            reader.close()
            if (results.isNotEmpty()) {
                emitter.onNext(results)
                emitter.onComplete()
                return@create
            }
            emitter.onError(Throwable("No Data"))
        }

    }

    private fun getPlantsFromLocal(context: Context, q: String): Observable<List<PlantInfo>> {
        return Observable.create { emitter ->
            val results = mutableListOf<PlantInfo>()
            val reader = BufferedReader(InputStreamReader(context.assets.open("plant.csv")))
            var line: String?
            var i = 0
            var keyList: List<String> = arrayListOf()

            while (reader.readLine().also { line = it } != null) {
                val row: List<String> = line!!.split(",")
                if (row[0].startsWith("F_")) {
                    keyList = row
                    continue
                }

                if (keyList.isEmpty() || row.size != keyList.size) {
                    continue
                }

                val id = i
                val alsoKnown = row[keyList.indexOf("F_AlsoKnown")]
                val brief = row[keyList.indexOf("F_Brief")]
                val cid = row[keyList.indexOf("F_CID")]
                val code = row[keyList.indexOf("F_Code")]
                val family = row[keyList.indexOf("F_Family")]
                val feature = row[keyList.indexOf("F_Feature")]
                val application = row[keyList.indexOf("F_Function＆Application")]
                val genus = row[keyList.indexOf("F_Genus")]
                val geo = row[keyList.indexOf("F_Geo")]
                val keywords = row[keyList.indexOf("F_Keywords")]
                val location = row[keyList.indexOf("F_Location")]
                val nameEn = row[keyList.indexOf("F_Name_En")]
                val nameCh = row[keyList.indexOf("F_Name_Ch")]
                val nameLatin = row[keyList.indexOf("F_Name_Latin")]
                val pic01Alt = row[keyList.indexOf("F_Pic01_ALT")]
                val pic01Url = row[keyList.indexOf("F_Pic01_URL")]
                val pic02Alt = row[keyList.indexOf("F_Pic02_ALT")]
                val pic02Url = row[keyList.indexOf("F_Pic02_URL")]
                val pic03Alt = row[keyList.indexOf("F_Pic03_ALT")]
                val pic03Url = row[keyList.indexOf("F_Pic03_URL")]
                val pic04Alt = row[keyList.indexOf("F_Pic04_ALT")]
                val pic04Url = row[keyList.indexOf("F_Pic04_URL")]
                val summary = row[keyList.indexOf("F_Summary")]
                val update = row[keyList.indexOf("F_Update")]
                val videoUrl = row[keyList.indexOf("F_Vedio_URL")]
                val voice1Alt = row[keyList.indexOf("F_Voice01_ALT")]
                val voice1Url = row[keyList.indexOf("F_Voice01_URL")]
                val voice2Alt = row[keyList.indexOf("F_Voice02_ALT")]
                val voice2Url = row[keyList.indexOf("F_Voice02_URL")]
                val voice3Alt = row[keyList.indexOf("F_Voice03_ALT")]
                val voice3Url = row[keyList.indexOf("F_Voice03_URL")]
                val pdf1Alt = row[keyList.indexOf("F_pdf01_ALT")]
                val pdf1Url = row[keyList.indexOf("F_pdf01_URL")]
                val pdf2Alt = row[keyList.indexOf("F_pdf02_ALT")]
                val pdf2Url = row[keyList.indexOf("F_pdf02_URL")]

                if(!location.contains(q)){
                    continue
                }

                results.add(
                    PlantInfo(
                        id,
                        alsoKnown,
                        brief,
                        cid,
                        code,
                        family,
                        feature,
                        application,
                        genus,
                        geo,
                        keywords,
                        location,
                        nameEn,
                        nameCh,
                        nameLatin,
                        pic01Alt,
                        pic01Url,
                        pic02Alt,
                        pic02Url,
                        pic03Alt,
                        pic03Url,
                        pic04Alt,
                        pic04Url,
                        summary,
                        update,
                        videoUrl,
                        voice1Alt,
                        voice1Url,
                        voice2Alt,
                        voice2Url,
                        voice3Alt,
                        voice3Url,
                        pdf1Alt,
                        pdf1Url,
                        pdf2Alt,
                        pdf2Url
                    )
                )
                i++
            }
            reader.close()
            if (results.isNotEmpty()) {
                emitter.onNext(results)
                emitter.onComplete()
                return@create
            }
            emitter.onError(Throwable("No Data"))
        }

    }

}