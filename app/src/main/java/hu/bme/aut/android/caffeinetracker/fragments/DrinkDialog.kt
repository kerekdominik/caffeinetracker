package hu.bme.aut.android.caffeinetracker.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.caffeinetracker.R
import hu.bme.aut.android.caffeinetracker.databinding.FragmentDrinkDialogBinding
import hu.bme.aut.android.caffeinetracker.data.CaffeineDrink

class DrinkDialog : DialogFragment(R.layout.fragment_drink_dialog) {

    interface DrinkDialogListener {
        fun onCaffeineDrinkCreated(newDrink: CaffeineDrink)
    }

    private lateinit var listener: DrinkDialogListener

    private lateinit var binding: FragmentDrinkDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        listener = context as? NewDrinkDialogListener
//            ?: throw RuntimeException("Activity must implement the NewDrinkDialogListener interface!")
    }

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDrinkDialogBinding.inflate(LayoutInflater.from(context))
        binding.spDrinkType.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.category_items)
        )

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title)
            .setView(binding.root)
            .setPositiveButton(R.string.addDrink) { dialog, which ->
                val caffeineDrink = getDrink()
                val bundle = bundleOf("caffeineDrink" to caffeineDrink)
                findNavController().navigate(R.id.drinks, bundle)
            }
            .setNegativeButton(R.string.cacnelDrink, null)
            .create()
    }

    private fun getDrink() = CaffeineDrink(
        drinkCategory = CaffeineDrink.DrinkCategory.getByOrdinal(binding.spDrinkType.selectedItemPosition)
            ?: CaffeineDrink.DrinkCategory.AMERICANO,
        comment = binding.etComment.text.toString()
    )

    companion object {
        const val TAG = "NewDrinkDialogFragment"
    }

}