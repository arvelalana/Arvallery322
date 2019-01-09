package com.arval.arvalgallery.service;


import com.arval.arvalgallery.object.Image;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @GET("http://pastebin.com/raw/wgkJgazE")
    Call<Image> loadHome();

}
