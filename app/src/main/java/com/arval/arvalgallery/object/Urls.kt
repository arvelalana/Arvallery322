package com.arval.arvalgallery.`object`

/**
 * Created by Arvel on 09/01/2019.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Urls {

    @SerializedName("raw")
    @Expose
    var raw: String? = null
    @SerializedName("full")
    @Expose
    var full: String? = null
    @SerializedName("regular")
    @Expose
    var regular: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

}