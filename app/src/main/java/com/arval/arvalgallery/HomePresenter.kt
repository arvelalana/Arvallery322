package com.arval.arvalgallery

import android.content.Context
import com.arval.arvalgallery.`object`.Image
import com.arval.arvalgallery.service.NetworkService
import com.arval.arvalgallery.service.ServiceFactory
import com.arval.arvalgallery.util.JSONUtil
import com.google.gson.reflect.TypeToken
import retrofit2.Call

/**
 * Created by Arvel on 09/01/2019.
 */

class HomePresenter(private val homeView: HomeContract.HomeView, private val mContext: Context) : HomeContract.HomeAction {
    private val service: NetworkService
    private var call: Call<Image>? = null

    init {
        this.service = ServiceFactory.createService()
    }

    override fun loadHome() {
        val homepageResp = JSONUtil.loadJSONFromAsset(mContext, R.raw.homepage, object : TypeToken<List<Image>>() {}.type) as List<Image>
        homeView.showHomepage(homepageResp)
    }
    fun stop() {
        if (null != call) {
            call!!.cancel()
        }
    }

}