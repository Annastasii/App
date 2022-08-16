package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapp.model.db.CountryDB
import com.example.myapp.screens.info.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var currentCountry: CountryDB
    private val vm: InfoViewModel by viewModels()
    var textView : TextView? = null
    var date : TextView? = null
    var recovered : TextView? = null
    var death : TextView? = null
    var confirmed : TextView? = null
    var totalConfirmed : TextView? = null
    var totalDeaths : TextView? = null
    var totalRecovered : TextView? = null
    var button : Button? = null
    var back : Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //  получение наименования страны при нажатии на item
        currentCountry = arguments?.getSerializable("country") as CountryDB

        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val country = currentCountry.country
        textView = view?.findViewById(R.id.coun)
        date = view?.findViewById(R.id.date)
        recovered = view?.findViewById(R.id.recovered)
        death = view?.findViewById(R.id.deaths)
        confirmed = view?.findViewById(R.id.confirmed)
        totalConfirmed = view?.findViewById(R.id.totalConfirmed)
        totalDeaths = view?.findViewById(R.id.totalDeaths)
        totalRecovered = view?.findViewById(R.id.totalRecovered)
        button = view?.findViewById(R.id.button)
        back = view?.findViewById(R.id.back)

        //  установить полученное название

       textView?.text = country

        //  данные из livedata
        vm.getConfirmed(country).observe(viewLifecycleOwner) { list ->
            list.map { item ->
               date?.text = "Date: ${item.date}"
                recovered?.text = "Recovered: ${item.recovered}"
                death?.text = "Deaths: ${item.deaths}"
                confirmed?.text = "Confirmed: ${item.confirmed}"
                totalConfirmed?.text = "Total Confirmed: ${item.totalConfirmed}"
                totalDeaths?.text = "Total Deaths: ${item.totalDeaths}"
                totalRecovered?.text = "TotalRecovered: ${item.totalRecovered}"
            }
        }

        //  нажатие на кнопку update
       button?.setOnClickListener {
            vm.setConfirmed()
            vm.getConfirmed(country)
        }

        //  нажатие на кнопку back
        back?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_infoFragment_to_startFragment)
        }
    }
}