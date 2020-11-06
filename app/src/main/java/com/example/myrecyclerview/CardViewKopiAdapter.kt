package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_cardview_coffee.view.*

class CardViewKopiAdapter(private val listKopi: ArrayList<Kopi>) : RecyclerView.Adapter<CardViewKopiAdapter.CardViewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_coffee, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listKopi[position])
    }

    override fun getItemCount(): Int = listKopi.size

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(kopi: Kopi) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(kopi.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)
                tv_item_name.text = kopi.name
                tv_item_description.text = kopi.description
                btn_set_favorite.setOnClickListener { Toast.makeText(itemView.context, "Favorite ${kopi.name}", Toast.LENGTH_SHORT).show() }
                btn_set_share.setOnClickListener { Toast.makeText(itemView.context, "Share ${kopi.name}", Toast.LENGTH_SHORT).show() }
                itemView.setOnClickListener { Toast.makeText(itemView.context, "Kamu memilih ${kopi.name}", Toast.LENGTH_SHORT).show() }
            }
        }
    }
}