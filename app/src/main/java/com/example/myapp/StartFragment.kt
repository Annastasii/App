package com.example.myapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.FragmentStartBinding
import com.example.myapp.model.db.CountryDB
import com.example.myapp.screens.start.Adapter
import com.example.myapp.screens.start.StartViewModel

class StartFragment : Fragment() {

    private var search: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var binding: FragmentStartBinding? = null
    var text: TextView? = null
    var adapter: Adapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]

        recyclerView = binding?.list
        adapter = Adapter()
        recyclerView?.adapter = adapter
        search = binding?.search

        //  инициализация базы данных
        viewModel.initDB()

        //  перенести данные из api в бд
        viewModel.setCountry()

        //  заполнение recyclerview
        viewModel.getCountry().observe(viewLifecycleOwner) { list ->
            adapter?.setList(list)
        }

        //  прослушиваение edittext
        search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val txt = p0.toString()
                val text = "%$txt%"

                // фильтрация списка
                viewModel.filter(text).observe(viewLifecycleOwner) { list ->
                    adapter?.setList(list)
                }
            }
        })
    }

    companion object {
        fun click(country: CountryDB) {
            val bundle = Bundle()
            bundle.putSerializable("country", country)
            MainActivity.app?.navController?.navigate(
                R.id.action_startFragment_to_infoFragment,
                bundle
            )
        }
    }
}