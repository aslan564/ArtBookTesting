package aslan.aslan.artbooktesting.db

import androidx.lifecycle.LiveData
import androidx.room.*
import aslan.aslan.artbooktesting.db.model.entity.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(art:Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts")
    fun getArtListFromDb():LiveData<List<Art>>
}