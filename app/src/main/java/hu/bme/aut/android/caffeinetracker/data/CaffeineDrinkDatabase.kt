package hu.bme.aut.android.caffeinetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CaffeineDrink::class], version = 1)
@TypeConverters(value = [CaffeineDrink.DrinkCategory::class])
abstract class CaffeineDrinkDatabase : RoomDatabase() {
    abstract fun caffeineDrinkDao(): CaffeineDrinkDao

    companion object {
        fun getDatabase(applicationContext: Context): CaffeineDrinkDatabase {
            return Room.databaseBuilder(
                applicationContext,
                CaffeineDrinkDatabase::class.java,
                "caffeineDrinksDB"
            ).build();
        }
    }
}