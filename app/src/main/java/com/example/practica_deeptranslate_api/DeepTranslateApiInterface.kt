package com.example.practica_deeptranslate_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepTranslateApiInterface {

    @POST("/language/translate/v2")
    fun setLanguage(@Body body: BodyTranslate?) : Call<TranslateResponse>

}