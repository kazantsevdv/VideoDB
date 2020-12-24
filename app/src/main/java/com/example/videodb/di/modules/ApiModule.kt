package com.example.videodb.di.modules

import com.example.videodb.App
import com.example.videodb.BuildConfig
import com.example.videodb.mvp.model.api.IDataSource
import com.example.videodb.mvp.model.network.INetworkStatus
import com.example.videodb.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl() = "https://api.themoviedb.org/3/"

    @Named("imgUrl")
    @Provides
    fun imgUrl() = "https://image.tmdb.org/t/p/w500"

    @Named("apiKey")
    @Provides
    fun apiKey() = "4e14e41304edf0add5eb84d4caaf5dc7"


    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson, client: OkHttpClient): IDataSource =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()


    @Singleton
    @Provides
    fun client(@Named("apiKey") key: String) =
        OkHttpClient.Builder().addInterceptor {
            val original = it.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", key)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build();
            it.proceed(request)

        }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}

