package com.example.brewbuddy.presentation.drinks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brewbuddy.R
import com.example.brewbuddy.data.db.CoffeeDatabase
import com.example.brewbuddy.data.db.coffeedb.CoffeeEntity
import com.example.brewbuddy.databinding.FragmentDrinkMenuBinding
import com.example.brewbuddy.domain.model.Coffee
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrinkMenuFragment : Fragment() {

    private var _binding: FragmentDrinkMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoffeeViewModel by viewModels()
    private lateinit var adapter: CoffeeAdapter
    private lateinit var db: CoffeeDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinkMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = CoffeeDatabase.getDatabase(requireContext())

        adapter = CoffeeAdapter(emptyList()) {
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.fetchCoffee()

        viewModel.coffeeList.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list ?: emptyList())
        }
        highlightButton(isCold = true)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return true
            }
        })

        binding.btnCold.setOnClickListener {
            highlightButton(isCold = true)
            viewModel.setCoffeeType(CoffeeViewModel.CoffeeType.COLD)
        }

        binding.btnHot.setOnClickListener {
            highlightButton(isCold = false)
            viewModel.setCoffeeType(CoffeeViewModel.CoffeeType.HOT)
        }
    }

    private fun filterList(query: String?) {
        val currentList = adapter.currentList()
        val filtered = if (query.isNullOrEmpty()) {
            currentList
        } else {
            currentList.filter { it.title.contains(query, ignoreCase = true) }
        }
        adapter.updateList(filtered)
    }

    private fun highlightButton(isCold: Boolean) {
        if (isCold) {
            binding.btnCold.setBackgroundColor(requireContext().getColor(R.color.select_bt))
            binding.btnCold.setTextColor(requireContext().getColor(R.color.unselect_bt))
            binding.btnHot.setBackgroundColor(requireContext().getColor(R.color.unselect_bt))
            binding.btnHot.setTextColor(requireContext().getColor(R.color.select_bt))
        } else {
            binding.btnHot.setBackgroundColor(requireContext().getColor(R.color.select_bt))
            binding.btnHot.setTextColor(requireContext().getColor(R.color.unselect_bt))

            binding.btnCold.setBackgroundColor(requireContext().getColor(R.color.unselect_bt))
            binding.btnCold.setTextColor(requireContext().getColor(R.color.select_bt))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
