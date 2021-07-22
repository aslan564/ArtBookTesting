package aslan.aslan.artbooktesting.repository.artInterface

import androidx.lifecycle.LiveData
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.roomDB.network.NetworkResult

interface ImageRepository {
    suspend fun insertArt(art: Art, onComplete: (id: Long) -> Unit)
    suspend fun deleteArt(art: Art, onComplete: (Boolean) -> Unit)
    fun getAllArtFromDB():LiveData<List<Art>>
    suspend fun getAllArtFromApi(): NetworkResult
    suspend fun searchArt(artName: String): NetworkResult
}