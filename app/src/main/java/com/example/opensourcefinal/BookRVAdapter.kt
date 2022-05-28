package com.example.opensourcefinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookRVAdapter(val context : Context, val List : MutableList<BookMarkModel>) : RecyclerView.Adapter<BookRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.store_list_rv, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : BookMarkModel){
            val Name = itemView.findViewById<TextView>(R.id.storeName)
            val address = itemView.findViewById<TextView>(R.id.storeAddress)

            Name.text = item.name
            address.text = item.loc
        }
    }
}