package aslan.aslan.artbooktesting.viewModel.artDetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.roomDB.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val repository: ImageRepository,
    application: Application
) : AndroidViewModel(application) {


    fun addImageToDb(art: ImageResultPOJO, name: String, yearInt: Int, artistName: String, onComplete:(id:Long)->Unit) = viewModelScope.launch {
        val response= withContext(Dispatchers.Default){

            Art(
                id = art.id.toLong(),
                year = yearInt,
                artistName = artistName,
                type = art.type,
                name = name,
                largeImageURL = art.largeImageURL,
                userId = art.userId,
                previewURL = art.previewURL
            )
        }
        repository.insertArt(art = response){
            onComplete(it)
        }
    }

}