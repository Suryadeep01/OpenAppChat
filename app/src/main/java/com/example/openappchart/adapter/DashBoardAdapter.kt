package com.example.openappchart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openappchart.OpenInModel.TopLinks
import com.example.openappchart.R

class DashBoardAdapter(topLinks: TopLinks) : RecyclerView.Adapter<DashBoardAdapter.ViewHolder>() {
    var links = topLinks;
    inner  class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var img: ImageView = itemView.findViewById(R.id.image)
        var click: TextView = itemView.findViewById(R.id.clicks)
        var context = itemView.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dash_board_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return links.data.top_links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.context)
            .load(links.data.top_links[position].original_image)
            .into(holder.img)
        holder.click.text = links.data.top_links[position].total_clicks.toString()
    }

}