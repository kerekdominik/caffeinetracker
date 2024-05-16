package hu.bme.aut.android.caffeinetracker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.caffeinetracker.R
import hu.bme.aut.android.caffeinetracker.data.CaffeineDrink
import hu.bme.aut.android.caffeinetracker.databinding.DrinkRowBinding
import hu.bme.aut.android.caffeinetracker.databinding.FragmentDrinksBinding

class DrinkAdapter(private val listener: DrinkClickListener)
    : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    private val caffeineDrinks = mutableListOf<CaffeineDrink>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DrinkViewHolder (
        DrinkRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val caffeineDrink = caffeineDrinks[position]

        holder.binding.ivIcon.setImageResource(getImageResource(caffeineDrink.drinkCategory))
        holder.binding.tvCategory.text = caffeineDrink.drinkCategory.toString()
        holder.binding.tvComment.text = caffeineDrink.comment
        // holder.binding.tvTime.text = caffeineDrink.time.toString() //TODO: set time

        holder.binding.ibRemove.setOnClickListener{
            listener.deleteCaffeineDrink(caffeineDrink)
            caffeineDrinks.remove(caffeineDrink)
            notifyDataSetChanged()
        }
    }

    fun addItem(item: CaffeineDrink) {
        this.caffeineDrinks.add(item)
        notifyItemInserted(caffeineDrinks.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(caffeineDrinks: List<CaffeineDrink>) {
        this.caffeineDrinks.clear()
        this.caffeineDrinks.addAll(caffeineDrinks)
        notifyDataSetChanged()
    }

    @DrawableRes()
    private fun getImageResource(category: CaffeineDrink.DrinkCategory): Int {
        return when (category) {
            CaffeineDrink.DrinkCategory.AMERICANO -> R.drawable.americano
            CaffeineDrink.DrinkCategory.CAFFE_LATTE -> R.drawable.caffe_latte
            CaffeineDrink.DrinkCategory.CAPPUCCINO -> R.drawable.cappuccino
            CaffeineDrink.DrinkCategory.ESPRESSO -> R.drawable.espresso
            CaffeineDrink.DrinkCategory.ESPRESSO_MACCHIATO -> R.drawable.espresso_macchiato
            CaffeineDrink.DrinkCategory.LATTE_MACCHIATO -> R.drawable.latte_macchiato
            CaffeineDrink.DrinkCategory.LUNGO -> R.drawable.lungo
        }
    }

    override fun getItemCount(): Int = caffeineDrinks.size


    interface DrinkClickListener {
        fun onCaffeineDrinkChanged(drink: CaffeineDrink)
        fun deleteCaffeineDrink(drink: CaffeineDrink)
    }

    inner class DrinkViewHolder(val binding: DrinkRowBinding): RecyclerView.ViewHolder(binding.root) //{
//        val category: TextView = itemView.findViewById(R.id.tvCategory)
//        val comment: TextView = itemView.findViewById(R.id.tvComment)
//    }
}
