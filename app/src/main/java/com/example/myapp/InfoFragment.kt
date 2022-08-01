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

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentCountry: CountryDB
    private val vm: InfoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        val view = binding

        //  получение наименования страны при нажатии на item
        currentCountry = arguments?.getSerializable("country") as CountryDB
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val country = currentCountry.country

        //  установить полученное название
        binding.coun.text = country

        //  перенести данные из api в бд
        vm.setConfirmed()

        //  данные из livedata
        vm.getConfirmed(country).observe(viewLifecycleOwner) { list ->
            list.getOrNull(0)?.let { confirmed ->
                binding.date.text = "Date: ${confirmed.date}"
                binding.recovered.text = "Recovered: ${confirmed.recovered}"
                binding.deaths.text = "Deaths: ${confirmed.deaths}"
                binding.confirmed.text = "Confirmed: ${confirmed.confirmed}"
                binding.totalConfirmed.text = "Total Confirmed: ${confirmed.totalConfirmed}"
                binding.totalDeaths.text = "Total Deaths: ${confirmed.totalDeaths}"
                binding.totalRecovered.text = "TotalRecovered: ${confirmed.totalRecovered}"
            }
        }

        //  нажатие на кнопку update
        binding.button.setOnClickListener {
            vm.setConfirmed()
            vm.getConfirmed(country)
        }

        //  нажатие на кнопку back
        binding.back.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_infoFragment_to_startFragment)
        }
    }
}