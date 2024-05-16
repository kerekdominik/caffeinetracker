package hu.bme.aut.android.caffeinetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.caffeinetracker.R
import hu.bme.aut.android.caffeinetracker.databinding.FragmentHomeBinding
import hu.bme.aut.android.caffeinetracker.data.CaffeineDrink

class Home : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddCaffeine.setOnClickListener {
            findNavController().navigate(R.id.drinkDialog)
        }

        binding.btnViewDrinks.setOnClickListener {
            findNavController().navigate(R.id.drinks)
        }
    }
}