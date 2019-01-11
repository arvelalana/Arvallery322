package com.arval.arvalgallery.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arval.arvalgallery.PreviewActivity
import com.arval.loader.ArvalLoader
import com.arval.arvalgallery.R
import com.arval.arvalgallery.`object`.Image
import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter
import kotlinx.android.synthetic.main.vh_gallery.view.*
import java.text.SimpleDateFormat
import java.util.*


class GalleryAdapter(val context: Context, var images: List<Image>) : MultiChoiceAdapter<GalleryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.vh_gallery, parent, false);
        return GalleryAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image: Image = images.get(position)
        holder.itemView.iv_thumbnail.setOnClickListener {
            val intent: Intent = Intent(context, PreviewActivity::class.java)
            intent.putExtra("url", image.urls?.regular!!)
            context.startActivity(intent)
        }

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

    fun updateList(images: List<Image>) {
        this.images = images

        Log.i("updateList", this.images.toString())
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(image: Image) {

            var like: Boolean? = image.likedByUser
            var likeCount: Int? = image?.likes

            ArvalLoader.loadImage(image.urls?.regular!!,itemView.iv_thumbnail)
            ArvalLoader.loadImage(image.user?.profileImage?.medium!!,itemView.iv_profile)

            itemView.tv_profile_name.setText(image.user?.name)
            itemView.tv_like_count.setText(likeCount.toString())
            var categoriesStringList: MutableList<String> = mutableListOf()
            for (i in 0 until image.categories!!.size step 1) {
                categoriesStringList.add(image.categories?.get(i)?.title!!)
            }
            itemView.tg_tags.setTags(categoriesStringList)

            if (like!!) {
                itemView.iv_like.setImageResource(R.drawable.ic_like)
            }

            itemView.iv_like.setOnClickListener(View.OnClickListener {
                like = !like!!
                if (like!!) {
                    itemView.iv_like.setImageResource(R.drawable.ic_like)
                    likeCount = likeCount?.plus(1)
                } else {
                    itemView.iv_like.setImageResource(R.drawable.ic_unlike)
                    likeCount = likeCount?.minus(1)
                }
                itemView.tv_like_count.setText(likeCount.toString())
            })

            var date = image.createdAt?.substringBefore("T")
            var spf = SimpleDateFormat("yyyy-MM-dd")
            val newDate = spf.parse(date)
            spf = SimpleDateFormat("dd MMM yyyy")
            date = spf.format(newDate)
            itemView.tv_date.setText(date)
        }
    }
}