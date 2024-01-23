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
import com.example.jbe_app.databinding.FragmentFirstBinding
import com.example.jbe_app.viewmodel.BreweryListModelView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment(), BreweryAdapter.BreweryListener {

    private val db by lazy { AppDatabase.getInstance(requireContext()) }
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BreweryListModelView
    private lateinit var adapter: BreweryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[BreweryListModelView::class.java]

        // Initialize the adapter
        adapter = BreweryAdapter(FirstFragment(), db)

        // Setup RecyclerView
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

        // Setup FAB click listener for navigation
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonFirst.setOnClickListener {

            viewModel.fetchAllPosts()
            viewModel.postModelListLiveData.observe(viewLifecycleOwner, Observer { posts ->
                if (posts != null) {
                    binding.rvHome.visibility = View.VISIBLE
                    adapter.setData(posts as ArrayList<PostModel>)
                } else {
                    Log.i("tag", "Something went wrong")
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemDeleted(it1: PostModel, position: Int) {
    }
}