package com.arval.arvalgallery.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arval.arvalgallery.PreviewActivity
import com.arval.arvalgallery.R
import com.arval.arvalgallery.`object`.Image
import com.bumptech.glide.Glide
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter
import kotlinx.android.synthetic.main.vh_gallery.view.*


class GalleryAdapter(val context: Context, var images: MutableList<Image>) : MultiChoiceAdapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.vh_gallery, parent, false);
        return GalleryAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image: Image = images.get(position)
//        holder.itemView.iv_image.setOnClickListener {
//
//            val intent: Intent = Intent(context, PreviewActivity::class.java)
//            intent.putExtra("imagePath", image.path)
//            context.startActivity(intent)
//        }

        holder.bindItems(image)
    }

    override fun setActive(view: View, state: Boolean) {
        super.setActive(view, state)
        Log.i("setActive", state.toString())
        if (state) {
        } else {

        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateList(images: MutableList<Image>) {
        this.images = mutableListOf()
        this.images = images

        Log.i("updateList", this.images.toString())
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(image: Image) {
//            Glide.with(itemView).load(Uri.fromFile(image.file)).into(itemView.iv_image)
//            itemView.iv_image.setImageURI(Uri.fromFile(image.file))
        }
    }
}