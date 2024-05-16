package hu.bme.aut.android.caffeinetracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CaffeineDrink(
    val drinkCategory: DrinkCategory,
    val comment: String
): Parcelable {
    enum class DrinkCategory {
        AMERICANO, CAFFE_LATTE, CAPPUCCINO, ESPRESSO, ESPRESSO_MACCHIATO, LATTE_MACCHIATO, LUNGO
    }
}