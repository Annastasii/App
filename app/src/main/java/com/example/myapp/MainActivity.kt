package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.model.country.CountryDB
import com.example.myapp.screens.start.Adapter
import com.example.myapp.screens.start.Listener
import com.example.myapp.screens.start.StartViewModel


class MainActivity : AppCompatActivity(), Listener{

    lateinit var search: EditText
    lateinit var text: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]

        recyclerView = binding.list
        adapter = Adapter(this)
        recyclerView.adapter = adapter
        search = binding.search

//        инициализация базы данных
        viewModel.initDB()

//        перенести данные из api в бд
       viewModel.setCountry()


        //        заполнение recyclerview
        viewModel.getCountry().observe(this) { list ->
            adapter.setList(list)
        }



//        прослушиваение edittext
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                val txt = p0.toString()
                val text = "%$txt%"

//                фильтрация списка
                viewModel.filter(text).observe(this@MainActivity) { list ->
                    adapter.setList(list)
                }
            }
        })
    }

//обработка нажатий на элемент списка
    override fun onClick(country: CountryDB) {
        val intent = Intent(applicationContext, InfoActivity::class.java)
        intent.putExtra("country", country.country)
        startActivity(intent)
    }

}