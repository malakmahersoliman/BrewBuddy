package com.example.brewbuddy.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.brewbuddy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvGreeting = view.findViewById<TextView>(R.id.tvGreeting)

        // Collect greeting
        viewLifecycleOwner.lifecycleScope.launch {
            vm.greeting.collect { tvGreeting.text = it }
        }

        // TODO: if you already have a RecyclerView for items,
        // collect vm.items here and submit to your adapter.
    }
}
