package com.example.brewbuddy.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brewbuddy.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        // Setup adapter
        adapter = FavoritesAdapter { fav ->
            viewModel.removeFavorite(fav.id)
        }
        binding.favoritesRecyclerView.adapter = adapter

        // Collect favorites
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favorites.collectLatest { list ->
                adapter.submitList(list)

                // Only toggle emptyText, header stays always visible
                binding.emptyText.isVisible = list.isEmpty()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
