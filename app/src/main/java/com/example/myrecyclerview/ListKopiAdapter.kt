package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_coffee.view.*

class ListKopiAdapter(private val listKopi: ArrayList<Kopi>) : RecyclerView.Adapter<ListKopiAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback ? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_coffee, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKopi[position])
    }

    override fun getItemCount(): Int = listKopi.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(kopi: Kopi) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(kopi.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo)
                tv_item_name.text = kopi.name
                tv_item_description.text = kopi.description

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(kopi) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Kopi)
    }
}