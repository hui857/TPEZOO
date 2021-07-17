package com.example.zoo.model.remote.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PlantResponse(
    val result: PlantResult
)

data class PlantResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<PlantInfo>
)

@kotlinx.parcelize.Parcelize
data class PlantInfo(
    @SerializedName("_id") val id: Int,
    @SerializedName("F_AlsoKnown") val alsoKnown: String,
    @SerializedName("F_Brief") val brief: String,
    @SerializedName("F_CID") val cid: String,
    @SerializedName("F_Code") val code: String,
    @SerializedName("F_Family") val family: String,
    @SerializedName("F_Feature") val feature: String,
    @SerializedName("F_Function＆Application") val application: String,
    @SerializedName("F_Genus") val genus: String,
    @SerializedName("F_Geo") val geo: String,
    @SerializedName("F_Keywords") val keywords: String,
    @SerializedName("F_Location") val location: String,
    @SerializedName("F_Name_En") val nameEn: String,
    @SerializedName(value = "\uFEFFF_Name_Ch") val nameCh: String,
    @SerializedName("F_Name_Latin") val nameLatin: String,
    @SerializedName("F_Pic01_ALT") val pic01Alt: String,
    @SerializedName("F_Pic01_URL") val pic01Url: String,
    @SerializedName("F_Pic02_ALT") val pic02Alt: String,
    @SerializedName("F_Pic02_URL") val pic02Url: String,
    @SerializedName("F_Pic03_ALT") val pic03Alt: String,
    @SerializedName("F_Pic03_URL") val pic03Url: String,
    @SerializedName("F_Pic04_ALT") val pic04Alt: String,
    @SerializedName("F_Pic04_URL") val pic04Url: String,
    @SerializedName("F_Summary") val summary: String,
    @SerializedName("F_Update") val update: String,
    @SerializedName("F_Vedio_URL") val videoUrl: String,
    @SerializedName("F_Voice01_ALT") val voice1Alt: String,
    @SerializedName("F_Voice01_URL") val voice1Url: String,
    @SerializedName("F_Voice02_ALT") val voice2Alt: String,
    @SerializedName("F_Voice02_URL") val voice2Url: String,
    @SerializedName("F_Voice03_ALT") val voice3Alt: String,
    @SerializedName("F_Voice03_URL") val voice3Url: String,
    @SerializedName("F_pdf01_ALT") val pdf1Alt: String,
    @SerializedName("F_pdf01_URL") val pdf1Url: String,
    @SerializedName("F_pdf02_ALT") val pdf2Alt: String,
    @SerializedName("F_pdf02_URL") val pdf2Url: String,
    ): Parcelable
