package com.example.practica_deeptranslate_api

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etTextToTranslate : EditText
    private lateinit var btnTranslate      : Button
    private lateinit var tvTranslatedText  : TextView
    private lateinit var tvSource          : TextView
    private lateinit var tvTarget          : TextView
    private lateinit var btnSwitchLanguage : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewComponents()

        var myImage  : Drawable? = ResourcesCompat.getDrawable(this.resources, R.drawable.ic_switch_2, null)

        btnSwitchLanguage.setOnClickListener {

            tvSource.text                = tvTarget.text.also { tvTarget.text = tvSource.text }
            btnSwitchLanguage.background = myImage.also { myImage = btnSwitchLanguage.background }


        }

        btnTranslate.setOnClickListener {
            translate()
        }
    }

    private fun translate() {


        val api           = DeepTranslateApiClient.retrofit.create(DeepTranslateApiInterface::class.java)
        val callTranslate = api.setLanguage(selectedLanguage())

        callTranslate?.enqueue(object : Callback<TranslateResponse> {
            override fun onResponse(
                    call: Call<TranslateResponse>,
                    response: Response<TranslateResponse>
            ) {
                val textTr            = response.body()?.data?.translations?.translatedText
                tvTranslatedText.text = textTr

            }

            override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                tvTranslatedText.text = t.message
            }

        })
    }

    private fun initViewComponents(){
        etTextToTranslate = findViewById(R.id.etTextToTranslate)
        btnTranslate      = findViewById(R.id.btnTranslate)
        tvTranslatedText  = findViewById(R.id.tvTranslatedText)
        btnSwitchLanguage = findViewById(R.id.btnSwitchLanguage)
        tvSource          = findViewById(R.id.tvSource)
        tvTarget          = findViewById(R.id.tvTarget)
    }

    private fun selectedLanguage() : BodyTranslate? {
        var body : BodyTranslate? = BodyTranslate(etTextToTranslate.text.toString(), null, null)

        if (tvSource.text.equals("Ingles") && tvTarget.text.equals("Español")) {
            body?.source = "en"
            body?.target = "es"
        }else {
            body?.source = "es"
            body?.target = "en"
        }


        return body
    }


}