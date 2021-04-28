package com.example.practica_deeptranslate_api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TranslateResponse(
    var data : Translation
)

@JsonClass(generateAdapter = true)
class Translation (
    var  translations : Translate
)

@JsonClass(generateAdapter = true)
data class Translate (
    var translatedText : String
)

data class BodyTranslate(
    var q : String?,
    var source : String?,
    var target : String?
)
