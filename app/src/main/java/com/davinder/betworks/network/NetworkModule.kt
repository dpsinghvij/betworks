package com.davinder.betworks.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        responseInterceptor: Interceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(responseInterceptor)
        .build()

    @Provides
    fun provideMockInterceptor(): Interceptor = MockInterceptor()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http:///login/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBetworksService(retrofit: Retrofit): BetworksService {
        return retrofit.create(BetworksService::class.java)
    }
}