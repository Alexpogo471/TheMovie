package ru.testAppMovie423432.themovie.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/movie/popular")
    fun movies(@Query("api_key") key:String, @Query("language") lang:String, @Query("page") page: String ) : Call<Root>

    companion object Factory {

        private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder().addInterceptor(interceptor)

        fun create(): MoviesApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org")
                .client(client.build())
                .build()

            return retrofit.create(MoviesApiService::class.java);
        }
    }
}