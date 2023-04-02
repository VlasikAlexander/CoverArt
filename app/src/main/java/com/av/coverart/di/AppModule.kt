package com.av.coverart.di

import com.av.coverart.common.Constants
import com.av.coverart.data.remote.LastfmApi
import com.av.coverart.data.repository.AlbumRepositoryImpl
import com.av.coverart.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLastfmApi() =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(createLogger()).build()
            )
            .build()
            .create(LastfmApi::class.java)

    private fun createLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }


    @Provides
    @Singleton
    fun provideAlbumRepository(api: LastfmApi): AlbumsRepository = AlbumRepositoryImpl(api)
}