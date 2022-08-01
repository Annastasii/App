package com.example.myapp.screens.start

import androidx.recyclerview.widget.DiffUtil
import com.example.myapp.model.db.CountryDB

/** обновление данных в списке*/
class NewDiffUtil(
    private val oldList: List<CountryDB>,
    private val newList: List<CountryDB>
) : DiffUtil.Callback() {

    // получение старого спика
    override fun getOldListSize(): Int {
        return oldList.size
    }

    // получение нового спика
    override fun getNewListSize(): Int {
        return newList.size
    }

    // сравнение списков
    // сравнение по id
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].country == newList[newItemPosition].country
    }

    // сравнение изменений
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].country != newList[newItemPosition].country
    }
}