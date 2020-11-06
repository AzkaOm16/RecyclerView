package com.example.myrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kopi(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable