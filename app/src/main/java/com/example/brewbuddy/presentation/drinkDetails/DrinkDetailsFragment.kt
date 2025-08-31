package com.example.brewbuddy.presentation.drinkDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentDrinkDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    private var _binding: FragmentDrinkDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DrinkDetailsViewModel by viewModels()
    private val args: DrinkDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinkDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Use the arguments directly instead of making API call
        binding.title.text = args.drinkTitle
        binding.price.text = args.drinkPrice
        binding.subtitle.text = args.description // Add description to nav args if needed

        Glide.with(requireContext())
            .load(args.image)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .centerCrop()
            .into(binding.drinkImage)

        viewModel.checkFavoriteStatus(args.drinkId)

        viewModel.quantity.observe(viewLifecycleOwner) { qty ->
            binding.Qty.text = qty.toString()
        }

        binding.plusBtn.setOnClickListener { viewModel.increaseQuantity() }
        binding.minusBtn.setOnClickListener { viewModel.decreaseQuantity() }
        binding.closeBtn.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        viewModel.isFavorite.observe(viewLifecycleOwner) { fav ->
            if (fav) {
                binding.favourite.setImageResource(R.drawable.ic_favorite )
            } else {
                binding.favourite.setImageResource(R.drawable.ic_favorite_border)
            }
        }
        binding.favourite.setOnClickListener {
            viewModel.toggleFavorite()
        }
binding.btnBuyNow.setOnClickListener {
    val action = DrinkDetailsFragmentDirections
        .actionDrinkDetailsFragmentToPaymentFragment(
            drinkId = args.drinkId,
            title=args.drinkTitle,
            quantity= viewModel.quantity.value ?: 1,
            price = args.drinkPrice,
            imageUrl = args.image
        )
    findNavController().navigate(action)
}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}