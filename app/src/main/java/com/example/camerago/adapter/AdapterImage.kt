package com.example.camerago.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camerago.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_layout_image.view.*

class AdapterImage(var mContext: Context):RecyclerView.Adapter<AdapterImage.ViewHolder>() {
        var mList:ArrayList<Bitmap> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_layout_image,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mList[position]
        holder.bindView(item)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(item:Bitmap) {
            itemView.image_view.setImageBitmap(item)
        }
    }

    fun setData(list:ArrayList<Bitmap>) {
        mList = list
        notifyDataSetChanged()
    }
}