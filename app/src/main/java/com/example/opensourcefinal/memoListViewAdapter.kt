package com.example.opensourcefinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.afinal.memoDataModel

class memoListViewAdapter(val List : MutableList<memoDataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //여기서 바인딩   뷰를 맵핑 해줘야함.
        var convertView = convertView   //변환되는 값이라 var
        if(convertView == null){
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.memolistview_item,parent,false)
        }
        val date = convertView?.findViewById<TextView>(R.id.dateArea)
        val memo = convertView?.findViewById<TextView>(R.id.textArea)

        date!!.text = List[position].date
        memo!!.text = List[position].memo   //date와 memo 넣어줘

        return convertView!!
    }
}