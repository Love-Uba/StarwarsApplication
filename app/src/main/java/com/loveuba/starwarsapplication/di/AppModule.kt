package com.loveuba.starwarsapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.loveuba.starwarsapplication.BuildConfig.BASE_URL
import com.loveuba.starwarsapplication.data.StarwarsService
import com.loveuba.starwarsapplication.data.repository.IStarwarsRepository
import com.loveuba.starwarsapplication.data.repository.StarwarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun makeGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideClient(
        logger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideClient(
//        logger: HttpLoggingInterceptor
//    ): UnsafeOkHttpClient {
//        return UnsafeOkHttpClient()
//    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL).build()
    }

    @Provides
    fun provideStarWarsService(retrofit: Retrofit): StarwarsService =
        retrofit.create(StarwarsService::class.java)

    @Provides
    @Singleton
    fun provideStarwarsRepository(apiService: StarwarsService): IStarwarsRepository {
        return StarwarsRepository(apiService)
    }

//    @Provides
//    fun provideSearchUseCase(starwarsRepository: IStarwarsRepository) =
//        SearchUseCase(starwarsRepository)
//
//    @Provides
//    fun provideDetailsUseCase(starwarsRepository: IStarwarsRepository) =
//        DetailsUseCase(starwarsRepository)
}