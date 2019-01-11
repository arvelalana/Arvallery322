package com.arval.arvalgallery.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import com.arval.arvalgallery.R

/**
 * Created by Arvel on 11/01/2019.
 */

class LoadingDialog(context: Context, cancelable: Boolean?) : Dialog(context) {
    private val ivLoading: ImageView? = null

    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCancelable(cancelable!!)
        this.setOnCancelListener {
            dismiss()
            (context as Activity).finish()
        }
        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        (context as Activity).finish()
    }

    fun init() {
        setContentView(R.layout.loading_dialog)
    }

    override fun show() {
        super.show()
    }


    override fun dismiss() {
        super.dismiss()
    }


}

