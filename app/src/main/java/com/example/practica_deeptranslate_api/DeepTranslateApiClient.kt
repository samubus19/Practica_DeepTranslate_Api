package com.example.practica_deeptranslate_api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DeepTranslateApiClient {

    companion object {

        private val baseURL = "https://deep-translate1.p.rapidapi.com"

        private val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        private val httpClient : OkHttpClient
            get() {
                val httpClientBuilder = OkHttpClient.Builder()
                httpClientBuilder.addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder()
                        .header("content-type",  "application/json")
                        .header("x-rapidapi-key", "59d934abddmshacf44220d5a67cbp1ef87cjsn24d2de510b22")
                        .header("x-rapidapi-host", "deep-translate1.p.rapidapi.com")
                        .header("useQueryString", "true")
                        .method(original.method(), original.body())
                        .build()

                    chain.proceed(request)
                }

                return httpClientBuilder.build()
            }

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()


    }

}