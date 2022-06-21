package com.example.myapp.screens.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.databinding.RecycleviewItemBinding
import com.example.myapp.model.country.CountryDB
import com.example.myapp.model.country.CountryItem
import kotlinx.android.synthetic.main.recycleview_item.view.*

class Adapter(val listener: Listener):RecyclerView.Adapter<Adapter.ViewHolder>() {

    var listStart = emptyList<CountryDB>()

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = RecycleviewItemBinding.bind(view)

        fun bind(country: CountryDB, listener: Listener) = with(binding){
            itemView.setOnClickListener{
                listener.onClick(country)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.countr.text = listStart[position].country
        holder.bind(listStart[position], listener)
    }

    override fun getItemCount(): Int {
        return listStart.size
    }

    fun setList(list: List<CountryDB>){
        listStart = list
        notifyDataSetChanged()
    }
    fun filterList(filterlist: ArrayList<CountryDB>){
        listStart = filterlist
        notifyDataSetChanged()
    }

}