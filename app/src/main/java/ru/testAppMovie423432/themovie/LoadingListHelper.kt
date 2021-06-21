package ru.testAppMovie423432.themovie

import androidx.recyclerview.widget.RecyclerView


class LoadingListHelper(recyclerView: RecyclerView) {
    private val layoutManager: RecyclerView.LayoutManager?
    private var loadingListener: LoadingListener? = null
    fun setScrollEndListener(loadingListener: LoadingListener?) {
        this.loadingListener = loadingListener
    }

    private val onScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.layoutManager!!.findViewByPosition(recyclerView.adapter!!.itemCount - 1) != null) {
                    if (loadingListener != null) {
                        loadingListener!!.onScrolledToEnd()
                    }
                }
            }
        }

    interface LoadingListener {
        fun onScrolledToEnd()
    }

    init {
        layoutManager = recyclerView.layoutManager
        recyclerView.addOnScrollListener(onScrollListener)
    }
}