package com.example.jbe_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jbe_app.data.AppDatabase
import com.example.jbe_app.data.PostModel
import com.example.jbe_app.databinding.FragmentSecondBinding
import com.example.jbe_app.viewmodel.BreweryListModelView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment() : Fragment() {
    private val db by lazy { AppDatabase.getInstance(requireContext()) }
    private var _binding: FragmentSecondBinding? = null
    private lateinit var adapter: BreweryAdapter
    private lateinit var viewModel: BreweryListModelView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("SecondFragment", "Just created")

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[BreweryListModelView::class.java]
        adapter = BreweryAdapter(viewModel)

        // Setup RecyclerView
        binding.rvHomeSaved.layoutManager = LinearLayoutManager(context)
        binding.rvHomeSaved.adapter = adapter

        // Fetch and observe breweries from the ViewModel
        viewModel.savedBreweriesLiveData.observe(viewLifecycleOwner, Observer { breweries ->
            if (breweries != null) {
                binding.rvHomeSaved.visibility = View.VISIBLE  // Access through binding
                adapter.setData(breweries)
            } else {
                Log.i("tag", "Something went wrong")
            }
        })

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}