package ru.testAppMovie423432.themovie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.testAppMovie423432.themovie.remote.MoviesApiService
import ru.testAppMovie423432.themovie.remote.Root


class MainActivity : AppCompatActivity(), OnclickListener {

    val listPosterImage = mutableListOf<String>()
    val listTitle = mutableListOf<String>()
    val listDescription = mutableListOf<String>()
    val listBackImage = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        MoviesApiService.create().movies("be0c75f0f3b5ea80836c15491035cd06", "ru", "1").enqueue(
            object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    response.body()?.results?.forEach { x -> listPosterImage.add("https://image.tmdb.org/t/p/w500" + x.posterPath) }
                    response.body()?.results?.forEach { x -> listTitle.add(x.title) }
                    response.body()?.results?.forEach { x -> listDescription.add(x.overview) }
                    response.body()?.results?.forEach { x -> listBackImage.add("https://image.tmdb.org/t/p/w500"+x.backdropPath) }
                    recyclerView.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    makeText(
                        applicationContext,
                        "Не удалось загрузить контент",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })



        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(listPosterImage, listTitle, this)
        recyclerView.adapter = adapter

    }

    override fun onClick(id : Long, position: Int) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("description",listDescription[position])
        intent.putExtra("backImage",listBackImage[position])
        startActivity(intent)
    }
}