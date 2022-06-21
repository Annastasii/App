package com.example.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.databinding.ActivityInfoBinding
import com.example.myapp.screens.info.InfoViewModel


class InfoActivity() : AppCompatActivity(){

//    lateinit var coun: TextView
//    lateinit var date: TextView
//    lateinit var active: TextView
//    lateinit var recovered: TextView
//    lateinit var deaths: TextView
//    lateinit var confirmed: TextView

    lateinit var binding: ActivityInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arguments = intent.extras
        val con = arguments?.get("country").toString()
        val country = con

        binding.coun.text = con

       val vm = ViewModelProvider(this)[InfoViewModel::class.java]

        //        инициализация базы данных
        vm.initDB()
//              перенести данные из api в бд
       vm.setConfirmed()
//
////      данные из livedata
        vm.getConfirmed(country).observe(this) { list ->
//            получить arraylist
            list.let{ item ->
//                получить объект
                item[0].let{ obj ->
//                    присвоить элементу значение из объкта
                    binding.date.text = "Date: ${obj.date}"
                    binding.recovered.text = "Recovered: ${obj.recovered}"
                    binding.deaths.text = "Deaths: ${obj.deaths}"
                    binding.confirmed.text = "Confirmed: ${obj.confirmed}"
                    binding.totalConfirmed.text = "Total Confirmed: ${obj.totalConfirmed}"
                    binding.totalDeaths.text = "Total Deaths: ${obj.totalDeaths}"
                    binding.totalRecovered.text = "TotalRecovered: ${obj.totalRecovered}"
                }
            }
        }
    }
    // нажатие на кнопку update
    fun onCliclickUpdate(view: View){
        val vm = ViewModelProvider(this)[InfoViewModel::class.java]
        vm.setConfirmed()
    }
}
