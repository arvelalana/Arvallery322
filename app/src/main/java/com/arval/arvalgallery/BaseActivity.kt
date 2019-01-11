package com.arval.arvalgallery

import android.support.v7.app.AppCompatActivity
import com.arval.arvalgallery.dialog.LoadingDialog

/**
 * Created by Arvel on 11/01/2019.
 */
public open class BaseActivity : AppCompatActivity() {

    var pd: LoadingDialog? = null

    fun setProgressIndicator(active: Boolean) {
        if (active) {
            pd = LoadingDialog(this, false)
            pd!!.show()
        } else {
            if (pd != null)
                pd!!.dismiss()
        }
    }
}