package ru.testAppMovie423432.themovie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val desc = findViewById<TextView>(R.id.tvDescription)
        val backImageView = findViewById<ImageView>(R.id.backImage)
        val arguments: Bundle? = intent.extras
        val description: String = arguments?.get("description").toString()
        val backImageUrl: String = arguments?.get("backImage").toString()
        Glide.with(this)
            .load(backImageUrl)
            .into(backImageView)
        desc.text = description
    }
}