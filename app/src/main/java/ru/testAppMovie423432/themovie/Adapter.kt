package ru.testAppMovie423432.themovie

import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class Adapter(private var listMovie: MutableList<Movie>?, private val listener: OnclickListener) : RecyclerView.Adapter<Adapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.titleText?.text = listMovie!![position].title
        holder.itemView.setOnClickListener { listener.onClick(holder.itemId,position) }
        Glide.with(holder.itemView.context)
            .load(listMovie!![position].posterImage)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.previewImage!!)
    }

    override fun getItemCount() = listMovie!!.size

    fun setMovies(movies: MutableList<Movie>){
        listMovie = movies
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var previewImage: ImageView? = null
        var titleText: TextView? = null

        init {
            previewImage = itemView.findViewById(R.id.posterImage)
            titleText = itemView.findViewById(R.id.titleTv)
            titleText?.movementMethod = ScrollingMovementMethod()
        }



    }

}