package ru.testAppMovie423432.themovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.testAppMovie423432.themovie.remote.MoviesApiService
import ru.testAppMovie423432.themovie.remote.Root

class MainViewModel : ViewModel() {

    var listMovie: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val movies = arrayListOf<Movie>()
    var page: Int = 1


    init {
        loadData()
    }

    private fun loadData(){
        MoviesApiService.create().movies(BuildConfig.API_KEY, "ru", page).enqueue(
            object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    for (i in response.body()?.results!!){
                    val movie = Movie(i.title,"https://image.tmdb.org/t/p/w500" + i.posterPath,i.overview,"https://image.tmdb.org/t/p/w500" + i.backdropPath)
                        movies.add(movie)
                    }
                    listMovie.value = movies
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    t.localizedMessage
                }

            })
    }

    fun addData(){
        page+=1
        MoviesApiService.create().movies("be0c75f0f3b5ea80836c15491035cd06", "ru", page).enqueue(
            object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    for (i in response.body()?.results!!){
                        val movie = Movie(i.title,"https://image.tmdb.org/t/p/w500" + i.posterPath,i.overview,"https://image.tmdb.org/t/p/w500" + i.backdropPath)
                        movies.add(movie)
                    }
                    listMovie.value = movies
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    t.localizedMessage
                }
            })
    }
}