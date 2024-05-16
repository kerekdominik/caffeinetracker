package hu.bme.aut.android.caffeinetracker.data

import androidx.room.*

@Dao
interface CaffeineDrinkDao {
    @Query("SELECT * FROM caffeineDrinksDB")
    fun getAll(): List<CaffeineDrink>

    @Insert
    fun insert(caffeineDrink: CaffeineDrink): Long

    @Update
    fun update(caffeineDrink: CaffeineDrink)

    @Delete
    fun deleteDrink(caffeineDrink: CaffeineDrink)
}