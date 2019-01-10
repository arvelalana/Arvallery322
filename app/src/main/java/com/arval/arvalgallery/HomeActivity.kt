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
import com.arval.*
import com.dinuscxj.refresh.RecyclerRefreshLayout

class HomeActivity : AppCompatActivity() ,HomeContract.HomeView {

    lateinit var adapter: GalleryAdapter
    lateinit var homePresenter:HomePresenter
    var images: List<Image> = emptyList()
    val activity: Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val mToolbar = findViewById(R.id.toolbar) as Toolbar
//        toolbar.title = "Gallery"
//        setSupportActionBar(mToolbar)
        homePresenter = HomePresenter(this,this)
        adapter = GalleryAdapter(this, images)

        ArvalLoader.setCache(ArvalCache())
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

//    fun scanAllImage() {
//        var currImage: Image = Image()
//        val fileList: List<String> = FileUtils.getImagePaths(this)
//        for (i in fileList) {
//            currImage = Image()
//            currImage.path = i
//            currImage.file = File(i)
//            currImage.date = Date(File(i).lastModified())
//            images.add(currImage)
//        }
//
//        val rand = Random()
//        val n = rand.nextInt(fileList.size)
//        Glide.with(activity).load(Uri.fromFile(images.get(n).file)).into(iv_header)
//
//        val sortedList: List<Image> = images.sortedWith(compareByDescending({ it.date }))
//        adapter.updateList(sortedList.toMutableList())
//    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            READ_STORAGE_PERMISSION_REQUEST_CODE -> {
//                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    scanAllImage()
//                } else {
//                    Toast.makeText(this, "Permission denied to read/ write your External storage", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.i("onActivityResult :", requestCode.toString() + " - " + resultCode)
//    }
}

