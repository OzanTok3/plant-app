package com.ozantok.plantapp.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryDto(
    val id: Int,
    val name: String,
    val title: String,
    val rank: Int,
    val image: ImageDto
) : Parcelable

@Parcelize
data class ImageDto(
    val url: String
) : Parcelable