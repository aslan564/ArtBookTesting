package aslan.aslan.artbooktesting.viewModel.artListFromDb

import androidx.lifecycle.*
import aslan.aslan.artbooktesting.db.model.pojo.ImageResponsePOJO
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.db.network.NetworkResult
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import aslan.aslan.artbooktesting.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDbViewModel @Inject  constructor(
    private val repository: ImageRepository
) : ViewModel() {

    val artList = repository.getAllArtFromDB()
    private var _uiState = MutableLiveData(Status.STABLE)
    val uiState: LiveData<Status>
        get() = _uiState

    private var _imageListFromApi = MutableLiveData<List<ImageResultPOJO>>(listOf())
    val imageListFromApi: LiveData<List<ImageResultPOJO>>
        get() = _imageListFromApi

    fun fetchImages() = viewModelScope.launch {
        _uiState.value = Status.LOADING
        when (val responsePOJO = repository.getAllArtFromApi()) {
            is NetworkResult.Success<*> -> {
                val data = responsePOJO.result as ImageResponsePOJO
                val listArt = data.hits
                _imageListFromApi.value = listArt
            }
            is NetworkResult.Failure -> {
                _uiState.value=Status.ERROR
            }
        }
        _uiState.postValue(Status.STABLE)
    }

}