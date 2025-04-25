package ru.vopros.courses.data.di

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vopros.courses.data.repository.CourseRepositoryImpl
import ru.vopros.courses.data.retrofit.CourseApi
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.domain.repository.CourseRepository

private val networkModule = module {
    single<Gson> {
        Gson().newBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
    }
    single<HttpLoggingInterceptor> { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .baseUrl(Utils.BASE_URL)
            .client(get<OkHttpClient>())
            .build()
    }
    single<CourseApi> { get<Retrofit>().create(CourseApi::class.java) }
}

private val repositoryModule = module {
    singleOf(::CourseRepositoryImpl) { bind<CourseRepository>() }
}

val dataModule = module {
    includes(
        networkModule,
        repositoryModule
    )
}
