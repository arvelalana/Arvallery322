package com.arval.arvalgallery

import android.app.Activity
import com.arval.arvalgallery.`object`.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arval.arvalgallery.adapter.GalleryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.arval.arvalgallery.dialog.LoadingDialog
import com.arval.loader.ArvalCache
import com.arval.loader.ArvalLoader
import com.dinuscxj.refresh.RecyclerRefreshLayout

class HomeActivity : BaseActivity(),HomeContract.HomeView {

    lateinit var adapter: GalleryAdapter
    lateinit var homePresenter:HomePresenter
    var images: List<Image> = emptyList()
    val activity: Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homePresenter = HomePresenter(this,this)
        adapter = GalleryAdapter(this, images)

        refresh_layout.setOnRefreshListener(RecyclerRefreshLayout.OnRefreshListener {
            refresh_layout.setRefreshing(true)
            homePresenter.loadHome()
        })
        rv_gallery_list.setLayoutManager(GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false))
        rv_gallery_list.adapter = adapter


        homePresenter.loadHome()


    }

    override fun showHomepage(images: List<Image>) {
        Log.i("showHomepage :", images.toString())
        adapter.updateList(images)
        refresh_layout.setRefreshing(false)

    }


}

