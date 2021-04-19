package aslan.aslan.artbooktesting.repository.artInterface

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.pojo.ImageResponsePOJO
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.db.model.pojo.ServerResponsePOJO
import aslan.aslan.artbooktesting.db.network.NetworkResult
import aslan.aslan.artbooktesting.util.Status

interface ImageRepository {
    suspend fun insertArt(art: Art, onComplete: (id: Long) -> Unit)
    suspend fun deleteArt(art: Art,onComplete: (Boolean) -> Unit)
    fun getAllArtFromDB(onComplete: (LiveData<List<Art>>) -> Unit)
    suspend fun getAllArtFromApi(): NetworkResult
    suspend fun searchArt(artName: String): NetworkResult
}