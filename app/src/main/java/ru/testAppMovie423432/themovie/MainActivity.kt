package ru.testAppMovie423432.themovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.testAppMovie423432.themovie.LoadingListHelper.LoadingListener


class MainActivity : AppCompatActivity(), OnclickListener{

    var listMovie = mutableListOf<Movie>()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = Adapter(listMovie, this)
        viewModel.listMovie.observe(this, {
            listMovie = it
            adapter.setMovies(it)
        })
        recyclerView.adapter = adapter
        LoadingListHelper(recyclerView)
            .setScrollEndListener(object : LoadingListener {
                override fun onScrolledToEnd() {
                    viewModel.addData()
                    adapter.notifyDataSetChanged()
                }
            })
    }

    override fun onClick(id: Long, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("description", listMovie[position].description)
        intent.putExtra("backImage", listMovie[position].backImage)
        startActivity(intent)
    }
}