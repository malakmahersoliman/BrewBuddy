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
import kotlin.getValue
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


        viewModel.loadDrink(args.drinkId)

        viewModel.drink.observe(viewLifecycleOwner) { drink ->
            binding.title.text = drink.title
            binding.subtitle.text = drink.description
            Glide.with(this).load(drink.image).into(binding.drinkImage)
        }

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
            quantity=args.drinkQuantity,
            price = args.drinkPrice,
        )
    findNavController().navigate(action)
}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}