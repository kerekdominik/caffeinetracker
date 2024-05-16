package hu.bme.aut.android.caffeinetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.caffeinetracker.R
import hu.bme.aut.android.caffeinetracker.adapter.DrinkAdapter
import hu.bme.aut.android.caffeinetracker.data.CaffeineDrink
import hu.bme.aut.android.caffeinetracker.data.CaffeineDrinkDatabase
import hu.bme.aut.android.caffeinetracker.databinding.FragmentDrinksBinding
import kotlin.concurrent.thread

class Drinks : Fragment(R.layout.fragment_drinks), DrinkAdapter.DrinkClickListener, DrinkDialog.DrinkDialogListener {
    private lateinit var binding: FragmentDrinksBinding

    private lateinit var adapter: DrinkAdapter
    private lateinit var database: CaffeineDrinkDatabase
    private lateinit var newCaffeineDrink: CaffeineDrink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = CaffeineDrinkDatabase.getDatabase(requireActivity().applicationContext)  //todo
        adapter = DrinkAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinksBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            newCaffeineDrink = bundle.getParcelable("caffeineDrink")!!
            onCaffeineDrinkCreated(newCaffeineDrink)
        }
    }

    private fun initRecyclerView() {
        adapter = DrinkAdapter(this)
        binding.rvDrinks.layoutManager = LinearLayoutManager(context)
        binding.rvDrinks.adapter = adapter
    }

    private fun loadDrinks() {
        thread {
            val drinks = database.caffeineDrinkDao().getAll()
            activity?.runOnUiThread {
                adapter.update(drinks)
            }
        }
    }

    override fun onCaffeineDrinkChanged(drink: CaffeineDrink) {
        thread {
            database.caffeineDrinkDao().update(drink)
        }
    }

    override fun deleteCaffeineDrink(drink: CaffeineDrink) {
        thread {
            database.caffeineDrinkDao().deleteDrink(drink)
        }
    }

    override fun onCaffeineDrinkCreated(newDrink: CaffeineDrink) {
        thread {
            val insertId = database.caffeineDrinkDao().insert(newDrink)
            newDrink.id = insertId
            activity?.runOnUiThread {
                adapter.addItem(newDrink)
            }
        }
    }

    companion object {
        const val TAG = "DrinksFragment"
    }
}