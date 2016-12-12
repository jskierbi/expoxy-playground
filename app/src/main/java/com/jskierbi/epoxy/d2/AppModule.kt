package com.jskierbi.epoxy.d2

import android.content.Context
import android.location.LocationManager
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.jskierbi.epoxy.base.BaseApplication
import com.jskierbi.epoxy.d2.qualifier.ForApplication
import com.jskierbi.epoxy.d2.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named

@Module
class AppModule(private val app: BaseApplication) {

    companion object {
        const val ENDPOINT_MYJSON = "ENDPOINT_MYJSON"
    }

    @Provides fun application() = app
    @Provides @ApplicationScope @ForApplication fun appContext() = app.applicationContext
    @Provides @ApplicationScope fun locationManager() = app.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // NETWORK
    @Provides @ApplicationScope internal fun provideJackson() = ObjectMapper().apply {
        registerKotlinModule()
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Provides fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
    }.build()

//    @Provides @Named(ENDPOINT_MYJSON) fun provideRetrofit(okHttpClient: OkHttpClient, jackson: ObjectMapper) = Retrofit.Builder().apply {
//        baseUrl("https://api.myjson.com/")
//        client(okHttpClient)
//        addConverterFactory(JacksonConverterFactory.create(jackson))
//        addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//    }.build()
//
//    @Provides fun provideShopApi(@Named(ENDPOINT_MYJSON) retrofit: Retrofit) = retrofit.create(ShopApiService::class.java)
}
