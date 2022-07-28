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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.FragmentStartBinding
import com.example.myapp.model.db.CountryDB
import com.example.myapp.screens.start.Adapter
import com.example.myapp.screens.start.Listener
import com.example.myapp.screens.start.StartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment(), Listener {

    private var search: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    var text: TextView? = null
    var adapter: Adapter? = null
    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        val view = binding
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {


        recyclerView = binding.list
        adapter = Adapter(this)
        recyclerView?.adapter = adapter
        search = binding.search

        //  перенести данные из api в бд
        viewModel.setCountry()

        //  заполнение recyclerview
        viewModel.getCountry().observe(viewLifecycleOwner) {
            adapter?.setList(it)
        }

        //  прослушиваение edittext
        search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                // фильтрация списка
                viewModel.filter("%$text%").observe(viewLifecycleOwner) {
                    adapter?.setList(it)
                }
            }
        })
    }

    override fun onClick(country: CountryDB) {
        val bundle = Bundle()
        bundle.putSerializable("country", country)
        (activity as MainActivity).navController.navigate(
            R.id.action_startFragment_to_infoFragment,
            bundle
        )
    }

}