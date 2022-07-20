package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapp.databinding.FragmentInfoBinding
import com.example.myapp.model.db.CountryDB
import com.example.myapp.screens.info.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null
    private lateinit var currentCountry: CountryDB
    private val vm: InfoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        //  получение наименования страны при нажатии на item
        currentCountry = arguments?.getSerializable("country") as CountryDB
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val country = currentCountry.country

        //  установить полученное название
        binding?.coun?.text = country

//        val vm = ViewModelProvider(this)[InfoViewModel::class.java]

        //  получить базу данных
        vm.getDB()

        //  перенести данные из api в бд
        vm.setConfirmed()

        //  данные из livedata
        vm.getConfirmed(country).observe(viewLifecycleOwner) { list ->
            list.getOrNull(0)?.let { obj ->
                binding?.date?.text = "Date: ${obj.date}"
                binding?.recovered?.text = "Recovered: ${obj.recovered}"
                binding?.deaths?.text = "Deaths: ${obj.deaths}"
                binding?.confirmed?.text = "Confirmed: ${obj.confirmed}"
                binding?.totalConfirmed?.text = "Total Confirmed: ${obj.totalConfirmed}"
                binding?.totalDeaths?.text = "Total Deaths: ${obj.totalDeaths}"
                binding?.totalRecovered?.text = "TotalRecovered: ${obj.totalRecovered}"
            }
        }

        //  нажатие на кнопку update
        binding!!.button.setOnClickListener {
            vm.setConfirmed()
            vm.getConfirmed(country)
        }

        //  нажатие на кнопку back
        binding!!.back.setOnClickListener {
            MainActivity.app?.navController?.navigate(R.id.action_infoFragment_to_startFragment)
        }
    }
}