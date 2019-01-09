package com.arval.arvalgallery

import com.arval.arvalgallery.`object`.Image

/**
 * Created by Arvel on 09/01/2019.
 */
class HomeContract {

    internal interface HomeAction {
        fun loadHome()
    }

    interface HomeView {
        fun showHomepage(images: List<Image>)
    }
}