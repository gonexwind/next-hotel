package com.gonexwind.nexthotel.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gonexwind.nexthotel.core.data.local.entity.HotelEntity

@Dao
interface HotelDao {
    @Query("SELECT * FROM hotel ORDER BY name DESC")
    fun getHotelForYou(): LiveData<List<HotelEntity>>

    @Query("SELECT * FROM hotel ORDER BY name DESC")
    fun getBestPick(): LiveData<List<HotelEntity>>

    @Query("SELECT * FROM hotel ORDER BY name DESC")
    fun getExplore(): LiveData<List<HotelEntity>>

    @Query("SELECT * FROM hotel WHERE isBookmarked = 1")
    fun getBookmarkedHotel(): LiveData<List<HotelEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHotel(hotel: List<HotelEntity>)

    @Update
    fun updateHotel(hotel: HotelEntity)

    @Query("DELETE FROM hotel WHERE isBookmarked = 0")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM hotel WHERE id = :id AND isBookmarked=1)")
    fun isHotelBookmarked(id: Int): Boolean
}