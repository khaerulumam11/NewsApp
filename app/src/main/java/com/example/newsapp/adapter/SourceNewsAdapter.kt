package com.example.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.SourceNewsModel

class SourceNewsAdapter (var context: Context) : RecyclerView.Adapter<SourceNewsAdapter.SourceNewsHolder>() {
    var list: ArrayList<SourceNewsModel.SourcesEntity> = ArrayList()

    init {
        notifyDataSetChanged()
    }

    fun setPost(list: ArrayList<SourceNewsModel.SourcesEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceNewsHolder {
        return SourceNewsHolder(
            LayoutInflater.from(context).inflate(R.layout.item_list_source_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SourceNewsHolder, position: Int) {
        var modelList:SourceNewsModel.SourcesEntity = list[position]
        holder.txtName!!.text = modelList.name
        holder.txtDesc!!.text = modelList.description
        holder.txtCategory!!.text = modelList.category.toString()

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SourceNewsHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var txtName: TextView = itemView!!.findViewById(R.id.tv_source_name)
        var txtDesc: TextView? = itemView!!.findViewById(R.id.tv_description_source)
        var txtCategory: TextView? = itemView!!.findViewById(R.id.tv_category_source)
        var gambar: ImageView? = itemView!!.findViewById(R.id.iv_source)

    }
}