package ru.testAppMovie423432.themovie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class Adapter(var listUrlImage: List<String>, var listTitle: List<String>, private val listener : OnclickListener) : RecyclerView.Adapter<Adapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.titleText?.text = listTitle[position]
        holder.itemId
//        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context,holder.titleText?.text,Toast.LENGTH_SHORT).show() }
        holder.itemView.setOnClickListener { listener.onClick(holder.itemId,position) }
        Glide.with(holder.itemView.context)
            .load(listUrlImage[position])
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.previewImage!!)
    }

    override fun getItemCount() = listTitle.size

    override fun onViewAttachedToWindow(holder: MoviesViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (layoutPosition == itemCount) {

        }
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var previewImage: ImageView? = null
        var titleText: TextView? = null

        init {
            previewImage = itemView.findViewById(R.id.imageView)
            titleText = itemView.findViewById(R.id.textView)

        }


    }

}