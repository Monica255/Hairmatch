package com.example.hairmatch.core.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    @SerializedName("top_prediction")
    var prediction: FaceType,
    val error:Boolean,
    val message:String,
):Parcelable

@Parcelize
data class FaceType(
    @SerializedName("class_index")
    val classIndex:Int,
    @SerializedName("class_label")
    val classLabel:String,
    @SerializedName("confidence")
    val confidence: Double,
    @SerializedName("recommendation")
    val recommendation:List<String>
):Parcelable
