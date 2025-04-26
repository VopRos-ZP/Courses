package ru.vopros.courses.data.di

import androidx.room.Room
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vopros.courses.data.repository.CourseRepositoryImpl
import ru.vopros.courses.data.repository.FavoritesRepositoryImpl
import ru.vopros.courses.data.repository.StoreRepositoryImpl
import ru.vopros.courses.data.retrofit.CourseApi
import ru.vopros.courses.data.room.AppDatabase
import ru.vopros.courses.domain.Utils
import ru.vopros.courses.domain.repository.CourseRepository
import ru.vopros.courses.domain.repository.FavoritesRepository
import ru.vopros.courses.domain.repository.StoreRepository

private val networkModule = module {
    single<Gson> {
        Gson().newBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
    }
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
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
    single<CourseApi> {
        get<Retrofit>()
            .create(CourseApi::class.java)
    }
}

private val roomModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = AppDatabase.NAME
        ).build()
    }
}

private val repositoryModule = module {
    singleOf(::CourseRepositoryImpl) { bind<CourseRepository>() }
    singleOf(::FavoritesRepositoryImpl) { bind<FavoritesRepository>() }
    singleOf(::StoreRepositoryImpl) { bind<StoreRepository>() }
}

val dataModule = module {
    includes(
        networkModule,
        roomModule,
        repositoryModule
    )
}
