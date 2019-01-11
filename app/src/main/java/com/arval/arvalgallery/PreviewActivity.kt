package com.arval.arvalgallery

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.arval.loader.ArvalLoader
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.vh_gallery.view.*
import java.io.File

class PreviewActivity : BaseActivity() {
    lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var bundle:Bundle = intent.extras
        url=  bundle.getString("url")

        ArvalLoader.loadImage(url,iv_preview)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.getItemId() == android.R.id.home) {
            finish(); //
        }
        return super.onOptionsItemSelected(item)
    }
}
