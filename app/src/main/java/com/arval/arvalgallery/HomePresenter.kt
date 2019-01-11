package com.arval.arvalgallery

import android.content.Context
import android.util.Log
import com.arval.loader.ArvalSync
import com.arval.loader.ArvalCallback
import com.arval.arvalgallery.`object`.Image
import com.arval.arvalgallery.service.NetworkService
import com.arval.arvalgallery.service.ServiceFactory
import com.google.gson.reflect.TypeToken
import retrofit2.Call

/**
 * Created by Arvel on 09/01/2019.
 */

class HomePresenter(private val homeView: HomeContract.HomeView, private val mContext: Context) : HomeContract.HomeAction, ArvalCallback {

    private val service: NetworkService
    private var call: Call<Image>? = null

    init {
        this.service = ServiceFactory.createService()
    }

    override fun loadHome() {
        homeView.setProgressIndicator(true)
//        val homepageResp = ArvalLoader.createRequest("http://pastebin.com/raw/wgkJgazE")
//        arvalRemoteCallback?.createRequest("http://pastebin.com/raw/wgkJgazE")
        ArvalSync(object : TypeToken<List<Image>>() {}.type, this).execute("http://pastebin.com/raw/wgkJgazE")
//        val homepageResp = JSONUtil.loadJSONFromAsset(mContext, R.raw.homepage, object : TypeToken<List<Image>>() {}.type) as List<Image>
    }

    override fun onFinish(output: Any?) {

        Log.i("output :", output.toString())
        homeView.showHomepage(output as List<Image>)
        homeView.setProgressIndicator(false)
    }

}