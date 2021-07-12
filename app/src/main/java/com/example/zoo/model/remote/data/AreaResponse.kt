package com.example.zoo.model.remote.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class AreaResponse(
    val result: AreaResult
)

data class AreaResult(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<AreaInfo>,
    val sort: String
)

@Parcelize
data class AreaInfo(
    @SerializedName("_id") val id: Int,
    @SerializedName("E_Category") val category: String,
    @SerializedName("E_Geo") val geo: String,
    @SerializedName("E_Info") val info: String,
    @SerializedName("E_Memo") val memo: String,
    @SerializedName("E_Name") val name: String,
    @SerializedName("E_Pic_URL") val picUrl: String,
    @SerializedName("E_URL") val url: String,
    @SerializedName("E_no") val no: String
): Parcelable


