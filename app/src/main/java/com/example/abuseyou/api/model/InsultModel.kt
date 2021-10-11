package com.example.abuseyou.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class InsultModel(
    val number: String,
    val language: String,
    val insult: String,
    val created: String,
    val shown: String,
    val createdby: String,
    val active: String,
    val comment: String,
) : Parcelable