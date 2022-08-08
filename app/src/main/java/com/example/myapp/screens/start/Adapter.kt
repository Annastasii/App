package com.example.myapp.screens.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.RecycleviewItemBinding
import com.example.myapp.model.db.CountryDB

class Adapter(private var listener: (list: CountryDB) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listCountry = emptyList<CountryDB>()

    inner class ViewHolder(private val binding: RecycleviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryDB: CountryDB) = with(binding) {
            country.text = countryDB.country
            itemView.setOnClickListener {
                listener(countryDB)
            }
        }
    }


    // идентификатор макета для отдельного списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecycleviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // связывание с данными
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCountry[position])
    }

    // возвращает количество элементов списка
    override fun getItemCount(): Int {
        return listCountry.size
    }

    fun setList(list: List<CountryDB>) {
        // передача списков
        val diffUtil = NewDiffUtil(listCountry, list)
        // сравненеие списков
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listCountry = list
        diffResult.dispatchUpdatesTo(this)
    }
}