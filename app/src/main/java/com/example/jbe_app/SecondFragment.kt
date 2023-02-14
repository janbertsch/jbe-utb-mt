package com.example.jbe_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jbe_app.data.PostModel
import com.example.jbe_app.databinding.ActivityMainBinding
import com.example.jbe_app.databinding.FragmentSecondBinding
import com.example.jbe_app.viewmodel.BreweryListModelView
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {


    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModelBroweryList: BreweryListModelView by lazy {
        ViewModelProvider(this).get(BreweryListModelView::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)


        return binding.root



        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        //binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        //binding.viewModel = viewModel

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemDeleted(it1: PostModel, position: Int) {

    }
}