package aslan.aslan.artbooktesting.repository

import androidx.lifecycle.LiveData
import aslan.aslan.artbooktesting.roomDB.ArtDao
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.roomDB.network.NetworkResult
import aslan.aslan.artbooktesting.roomDB.network.service.ArtsService
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitApi: ArtsService

) : ImageRepository {

    override suspend fun insertArt(art: Art, onComplete: (id: Long) -> Unit) {
        artDao.insert(art)
        onComplete(art.id)
    }


    override suspend fun deleteArt(art: Art, onComplete: (Boolean) -> Unit) {
        artDao.deleteArt(art)
        onComplete(true)
    }

    override fun getAllArtFromDB(onComplete: (LiveData<List<Art>>) -> Unit) {
        onComplete(artDao.getArtListFromDb())
    }


    override suspend fun getAllArtFromApi(): NetworkResult = withContext(Dispatchers.IO)
    {
        return@withContext try {
            val responsePOJO = retrofitApi.getAllArts()
            if (responsePOJO.isSuccessful) {
                responsePOJO.body()?.let {
                    return@let NetworkResult.Success(it)
                } ?: NetworkResult.Failure(responsePOJO.message())
            } else {
                NetworkResult.Failure(responsePOJO.message())
            }

        } catch (ex: Exception) {
            NetworkResult.Failure(ex.message ?: "")
        }
    }


    override suspend fun searchArt(artName: String): NetworkResult = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = retrofitApi.getSearchArts(artName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let NetworkResult.Success(it)
                } ?: NetworkResult.Failure(response.message())
            } else {
                NetworkResult.Failure(response.message())
            }
        } catch (ex: Exception) {
            NetworkResult.Failure(ex.message ?: "")
        }
    }
}