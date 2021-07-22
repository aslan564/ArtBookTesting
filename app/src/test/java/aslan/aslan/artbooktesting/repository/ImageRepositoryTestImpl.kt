package aslan.aslan.artbooktesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.roomDB.network.NetworkResult
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository

class ImageRepositoryTestImpl(

) : ImageRepository {

    private val artList= mutableListOf<Art>()
    var liveDataArtList= MutableLiveData<List<Art>>(listOf())

    override suspend fun insertArt(art: Art, onComplete: (id: Long) -> Unit) {
        artList.add(art)
        onComplete(art.id)
    }


    override suspend fun deleteArt(art: Art, onComplete: (Boolean) -> Unit) {
        artList.remove(art)
        onComplete(true)
    }

    override fun getAllArtFromDB(): LiveData<List<Art>> {
        return liveDataArtList
    }


    override suspend fun getAllArtFromApi(): NetworkResult {
        return NetworkResult.Success(artList)
    }

    override suspend fun searchArt(artName: String): NetworkResult {
        return NetworkResult.Success(artName)
    }


}