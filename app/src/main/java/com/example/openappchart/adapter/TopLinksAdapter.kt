package com.example.openappchart.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openappchart.OpenInModel.TopLinks
import com.example.openappchart.R


class TopLinksAdapter(data: TopLinks) : RecyclerView.Adapter<TopLinksAdapter.ViewHolder>()  {
    var links =  data

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.topLeftIcon)
        var clip: ImageView = itemView.findViewById(R.id.clip_board)
        var title: TextView = itemView.findViewById(R.id.Text)
        var right_side: TextView = itemView.findViewById(R.id.rightSideText)
        var link_text: TextView = itemView.findViewById(R.id.links_text)
        var date_text: TextView = itemView.findViewById(R.id.dateText)


        var context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_links_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return links.data.top_links.size
    }

    private fun setClipboard(context: Context, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
        } else {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)
        }
        showToast(context, "Text copied to clipboard")
    }
    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun openWebUrl(url: String,context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.context)
            .load(links.data.top_links[position].original_image)
            .into(holder.img)
        holder.right_side.text = links.data.top_links[position].total_clicks.toString()
        holder.date_text.text = links.data.top_links[position].created_at
        holder.link_text.text = links.data.top_links[position].web_link
        holder.link_text.setOnClickListener {
            openWebUrl(links.data.top_links[position].web_link,holder.context)
        }
        holder.clip.setOnClickListener{
            setClipboard(holder.context,links.data.top_links[position].web_link)
        }
        holder.title.text = links.data.top_links[position].title
    }
}
