package com.example.myapp.screens.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.StartFragment
import com.example.myapp.model.db.CountryDB
import kotlinx.android.synthetic.main.recycleview_item.view.*

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listStart = emptyList<CountryDB>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // идентификатор макета для отдельного списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return ViewHolder(view)
    }

    // связывание с данными
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.countr.text = listStart[position].country
    }

    // возвращает количество элементов списка
    override fun getItemCount(): Int {
        return listStart.size
    }

    fun setList(list: List<CountryDB>) {
        // передача списков
        val diffUtil = NewDiffUtil(listStart, list)
        // сравненеие списков
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listStart = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            StartFragment.click(listStart[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}