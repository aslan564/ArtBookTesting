package aslan.aslan.artbooktesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import aslan.aslan.artbooktesting.db.ArtDao
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.pojo.ImageResponsePOJO
import aslan.aslan.artbooktesting.db.model.pojo.ServerResponsePOJO
import aslan.aslan.artbooktesting.db.network.NetworkResult
import aslan.aslan.artbooktesting.db.network.service.ArtsService
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import aslan.aslan.artbooktesting.util.Status
import aslan.aslan.artbooktesting.util.networkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class ImageRepositoryTestImpl(

) : ImageRepository {

    private val artList= mutableListOf<Art>()
    var liveDataArtList= MutableLiveData<List<Art>>(listOf())

    override suspend fun insertArt(art: Art, onComplete: (id: Long) -> Unit) {
        artList.add(art)
        onComplete(art.id)
    }


    override suspend fun deleteArt(art: Art,onComplete: (Boolean) -> Unit) {
        artList.remove(art)
        onComplete(true)
    }

    override fun getAllArtFromDB(onComplete: (LiveData<List<Art>>) -> Unit) {
        onComplete(liveDataArtList)
    }

    override suspend fun getAllArtFromApi(): NetworkResult {
        TODO("Not yet implemented")
    }

    override suspend fun searchArt(artName: String): NetworkResult {
        TODO("Not yet implemented")
    }


}