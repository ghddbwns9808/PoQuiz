package com.poquiz.app.di

import com.poquiz.data.api.ApiClient
import com.poquiz.data.api.ApiInterface
import com.poquiz.data.api.PokeApiInterface
import com.poquiz.data.api.pokeApi
import com.poquiz.data.api.springApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


    @Provides
    @Singleton
    @springApi
    fun provideApiInterface(@springApi retrofit: Retrofit): ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    @springApi
    fun provideRetrofit(): Retrofit{
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @pokeApi
    fun providePokeApiInterface(@pokeApi retrofit: Retrofit): PokeApiInterface{
        return retrofit.create(PokeApiInterface::class.java)
    }

    @Singleton
    @Provides
    @pokeApi
    fun providePokeRetrofit(): Retrofit{
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiClient.POKE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}