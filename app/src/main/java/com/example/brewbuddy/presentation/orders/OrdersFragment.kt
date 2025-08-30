package com.example.brewbuddy.presentation.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brewbuddy.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private val orderViewModel: OrderViewModel by viewModels()

    private lateinit var orderAdapter: OrderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        observeOrders()

        // Load all orders by default
        orderViewModel.filterOrders("recent")
    }
    private fun setupRecyclerView() {
        orderAdapter = OrderAdapter()
        binding.rvOrders.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
    }
    private fun setupListeners() {
        binding.opt1.setOnClickListener {
            it.isSelected = true
            binding.opt2.isSelected = false
            orderViewModel.filterOrders("recent")
        }
        binding.opt2.setOnClickListener {
            it.isSelected = true
            binding.opt1.isSelected = false
            orderViewModel.filterOrders("past")
        }


    }
    private fun observeOrders() {
        viewLifecycleOwner.lifecycleScope.launch {
            orderViewModel.orders.collect { list ->
                orderAdapter.updateOrders(list)
                binding.emptyText.isVisible = list.isEmpty()

            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}
