package aslan.aslan.artbooktesting.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import aslan.aslan.artbooktesting.roomDB.model.entity.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(art: Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts WHERE  id=:key")
    fun get(key: Long): List<Art>

    @Query("SELECT * FROM arts ORDER BY id DESC")
    fun getArtListFromDb(): LiveData<List<Art>>
}