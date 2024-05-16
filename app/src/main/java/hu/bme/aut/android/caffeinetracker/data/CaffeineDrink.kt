package hu.bme.aut.android.caffeinetracker.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize

@Entity(tableName = "caffeineDrinksDB")
@Parcelize
data class CaffeineDrink(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "drink_category") var drinkCategory: DrinkCategory,
    @ColumnInfo(name = "comment") var comment: String
    // TODO: time picker
): Parcelable {
    enum class DrinkCategory {
        AMERICANO, CAFFE_LATTE, CAPPUCCINO, ESPRESSO, ESPRESSO_MACCHIATO, LATTE_MACCHIATO, LUNGO;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): DrinkCategory? {
                var ret: DrinkCategory? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(drinkType: DrinkCategory): Int {
                return drinkType.ordinal
            }
        }
    }
}

//data class CaffeineDrink(val category: String, val comment: String)