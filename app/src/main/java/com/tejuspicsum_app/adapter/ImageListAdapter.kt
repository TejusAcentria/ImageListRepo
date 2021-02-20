package com.tejuspicsum_app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tejuspicsum_app.ApiService.ApiService
import com.tejuspicsum_app.R
import com.tejuspicsum_app.model.ImageResponse

class ImageListAdapter(var itemsList: ArrayList<ImageResponse>, var activity: Activity,var onClickItemView: OnClickItemView) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface OnClickItemView{
        fun OnClompleteItemView(
            itemsList: ImageResponse
        )
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(activity).inflate(R.layout.image_list_layout, parent, false)
        return ImageListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemsList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ImageListViewHolder) {
            holder.setIsRecyclable(false)
            val items = itemsList!![position]

            Glide.with(activity).asBitmap().load(ApiService.Image_URL+items.id).into(holder.imageView)
           
            holder.authourName.text=items.author

            holder.itemView.setOnClickListener {
                onClickItemView.OnClompleteItemView(items)
            }

        }
    }


}


internal class ImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   var imageView=itemView.findViewById<ImageView>(R.id.imageView)
    var authourName=itemView.findViewById<TextView>(R.id.textView)

}