package ru.vopros.courses.data.retrofit

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import ru.vopros.courses.domain.Utils

interface CourseApi {

    @Streaming
    @GET("/uc")
    suspend fun getCourses(
        @Query("id") id: String = Utils.ID,
        @Query("export") export: String = Utils.EXPORT,
    ): Response<ResponseBody>

}