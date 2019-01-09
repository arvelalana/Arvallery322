package com.arval.arvalgallery

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_preview.*
import java.io.File

class PreviewActivity : AppCompatActivity() {
    lateinit var fileImage:File
    lateinit var filePath:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        var bundle:Bundle = intent.extras
        filePath=  bundle.getString("imagePath")

        fileImage = File(filePath)
        Glide.with(this).load(Uri.fromFile(fileImage)).into(iv_preview)
    }
}
