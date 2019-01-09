package com.arval.arvalgallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import com.arval.arvalgallery.`object`.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import com.arval.arvalgallery.adapter.GalleryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arval.arvalgallery.util.FileUtils
import com.arval.arvalgallery.util.MediaScannerWrapper
import com.arval.arvalgallery.util.PermissionUtil
import com.arval.arvalgallery.util.PermissionUtil.READ_STORAGE_PERMISSION_REQUEST_CODE
import com.bumptech.glide.Glide
import com.davidecirillo.multichoicerecyclerview.MultiChoiceToolbar
import kotlinx.android.synthetic.main.vh_gallery.*
import kotlinx.android.synthetic.main.vh_gallery.view.*
import java.io.File
import java.util.*
import javax.security.auth.login.LoginException
import com.arval.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: GalleryAdapter
    var images: MutableList<Image> = mutableListOf<Image>()
    val activity: Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mToolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = "Gallery"
        setSupportActionBar(mToolbar)

        adapter = GalleryAdapter(this, images)

        rv_gallery_list.setLayoutManager(GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false))
        rv_gallery_list.adapter = adapter


//        if (PermissionUtil.checkStorage(this)) {
//            scanAllImage()
//        } else {
//            try {
//                PermissionUtil.requestStorage(this)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }

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
