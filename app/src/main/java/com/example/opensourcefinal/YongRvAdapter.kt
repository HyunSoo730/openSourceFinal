package com.example.opensourcefinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opensource.GwangRvAdapter

class YongRvAdapter (val items : List<YongStore>) : RecyclerView.Adapter<YongRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YongRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_list_rv,parent,false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: YongRvAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item : YongStore){
            val storeName = itemView.findViewById<TextView>(R.id.storeName)
            storeName.text = item.name
            val storeAddress = itemView.findViewById<TextView>(R.id.storeAddress)
            storeAddress.text = item.address1

        }
    }


    //-----리사이클러뷰 클릭 이벤트 처리------
    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickLister : OnItemClickListener){
        this.itemClickListener = onItemClickLister
    }

    private lateinit var itemClickListener : OnItemClickListener
    //------------------------------------
}