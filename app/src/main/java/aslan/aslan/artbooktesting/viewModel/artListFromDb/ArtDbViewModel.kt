package aslan.aslan.artbooktesting.viewModel.artListFromDb

import android.app.Application
import androidx.lifecycle.*
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import aslan.aslan.artbooktesting.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDbViewModel @Inject constructor(
    private val repository: ImageRepository, application: Application
) : AndroidViewModel(application) {


    private var _uiState = MutableLiveData(Status.STABLE)
    val uiState: LiveData<Status>
        get() = _uiState


    fun fetchImages(onComplete: (LiveData<List<Art>>) -> Unit) = viewModelScope.launch {
        _uiState.postValue(Status.LOADING)
        repository.getAllArtFromDB {
            onComplete(it)
            _uiState.postValue(Status.SUCCESS)
        }
    }

    fun deleteArt(art: Art)=viewModelScope.launch {
        _uiState.postValue(Status.LOADING)
        repository.deleteArt(art){
            _uiState.postValue(Status.SUCCESS)
        }

    }


}