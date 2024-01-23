package com.example.jbe_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jbe_app.data.AppDatabase
import com.example.jbe_app.data.PostModel
import com.example.jbe_app.databinding.FragmentSecondBinding
import com.example.jbe_app.viewmodel.BreweryListModelView
import kotlinx.android.synthetic.main.content_main.nav_host_fragment_content_main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment() : Fragment() {
    private val db by lazy { AppDatabase.getInstance(requireContext()) }
    private var _binding: FragmentSecondBinding? = null
    private lateinit var adapter: BreweryAdapter
    private lateinit var viewModel: BreweryListModelView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModelBroweryList: BreweryListModelView by lazy {
        ViewModelProvider(this)[BreweryListModelView::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        Log.i("SecondFragment", "Just created")

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        //db = AppDatabase.getInstance(requireContext())
        return binding.root

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        //binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        //binding.viewModel = viewModel

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BreweryAdapter(FirstFragment(), db)

        // Setup RecyclerView
        binding.rvHomeSaved.layoutManager = LinearLayoutManager(context)
        binding.rvHomeSaved.adapter = adapter

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[BreweryListModelView::class.java]

        // Fetch and observe breweries from the ViewModel
        viewModel.fetchSavedBreweries() // Fetching might be different based on your implementation
        viewModel.savedBreweriesLiveData.observe(viewLifecycleOwner, Observer { breweries ->
            if (breweries != null) {
                binding.rvHomeSaved.visibility = View.VISIBLE  // Access through binding
                adapter.setData(breweries as ArrayList<PostModel>)
            } else {
                Log.i("tag", "Something went wrong")
            }
        })

        // Start a coroutine in the lifecycle scope of the fragment
        viewLifecycleOwner.lifecycleScope.launch {
            // Run database operation in IO thread
            val savedBreweries = withContext(Dispatchers.IO) {
                db.postModelDao().getAll()
            }

            // Update your RecyclerView or other UI components with 'savedBreweries'
            // This is already on the main thread, so no need to call runOnUiThread
            adapter.setData(savedBreweries)
        }

        // ...
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemDeleted(it1: PostModel, position: Int) {

    }
}