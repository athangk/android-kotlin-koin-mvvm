package com.coding.mymvvmkoin.di.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Dynamic network api for all classes
 */
class NetworkService  : KoinComponent {

    /**
     * Create retrofit returning the class on args
     */
    fun<T> callAPI(service: Class<T>, apiURL:String): T{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val interceptorClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiURL)
                .client(interceptorClient)
                .build()

        return retrofit.create(service)
    }

}

