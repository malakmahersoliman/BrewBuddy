package com.example.brewbuddy.presentation.onboarding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.brewbuddy.R
import com.example.brewbuddy.data.local.prefs.Prefs
import com.example.brewbuddy.databinding.FragmentEnternameBinding

class EnterNameFragment : Fragment() {

    private var _binding: FragmentEnternameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentEnternameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.etName.doAfterTextChanged { binding.tilName.error = null }

        binding.btnContinue.setOnClickListener {
            val name = binding.etName.text?.toString()?.trim().orEmpty()
            if (name.isBlank()) {
                binding.tilName.error = "Name is required"
                return@setOnClickListener
            }
            Prefs.userName = name
            val opts = navOptions { popUpTo(R.id.onboardingFragment) { inclusive = true } }
            findNavController().navigate(R.id.homeFragment, null, opts)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
