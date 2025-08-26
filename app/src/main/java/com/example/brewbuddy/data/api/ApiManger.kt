package com.example.brewbuddy.data.api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiManager {
    const val baseUrl = "https://api.sampleapis.com/"
    private var retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getWebServices() =retrofit.create(WebServices::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return client
    }

}