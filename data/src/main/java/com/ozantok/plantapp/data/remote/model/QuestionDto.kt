package com.ozantok.plantapp.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionDto(
    val id: Int,
    val title: String,
    val subtitle: String,
    @SerializedName("image_uri")
    val imageUri: String,
    val uri: String,
    val order: Int
) : Parcelable