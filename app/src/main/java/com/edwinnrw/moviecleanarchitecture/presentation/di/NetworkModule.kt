package com.edwinnrw.moviecleanarchitecture.presentation.di

import android.app.Application
import com.edwinnrw.moviecleanarchitecture.BuildConfig
import com.edwinnrw.moviecleanarchitecture.data.api.RemoteApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    internal fun provideKotlinSerializationConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()

        val json = Json {
            ignoreUnknownKeys = true // helpful for unknown fields in response
            isLenient = true
            encodeDefaults = true
        }
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.cache(cache)
        httpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.connectTimeout(5,TimeUnit.MINUTES)
        if(BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)
        }
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()

    }
    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit) : RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }


}