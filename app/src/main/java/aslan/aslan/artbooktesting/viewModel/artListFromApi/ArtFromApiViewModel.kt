package aslan.aslan.artbooktesting.viewModel.artListFromApi

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.*
import aslan.aslan.artbooktesting.roomDB.model.pojo.ImageResponsePOJO
import aslan.aslan.artbooktesting.roomDB.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.roomDB.network.NetworkResult
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import aslan.aslan.artbooktesting.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtFromApiViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

   /* init {
        fetchImages()
    }*/

    private var _uiStatus = MutableLiveData(Status.STABLE)
    val uiStatus: LiveData<Status>
        get() = _uiStatus

    private var _uiStatusMessage = MutableLiveData<String?>()
    val uiStatusMessage: LiveData<String?>
        get() = _uiStatusMessage


    private var _imageListFromApi = MutableLiveData<List<ImageResultPOJO>>(listOf())
    val imageListFromApi: LiveData<List<ImageResultPOJO>>
        get() = _imageListFromApi

     fun fetchImages() = viewModelScope.launch {
        _uiStatus.value = Status.LOADING
         _uiStatusMessage.postValue("Please wait")
        when (val responsePOJO = repository.getAllArtFromApi()) {
            is NetworkResult.Success<*> -> {
                val data = responsePOJO.result as ImageResponsePOJO
                val listArt = data.hits
                _imageListFromApi.value = listArt
                _uiStatusMessage.postValue(null)
            }
            is NetworkResult.Failure -> {
                _uiStatus.value = Status.ERROR
                _uiStatusMessage.postValue("Check your Network connection")
            }
        }
        _uiStatus.postValue(Status.STABLE)
    }

    fun searchImageBind(editText: EditText){
        editText.addTextChangedListener {editable->
            viewModelScope.launch {
                delay(1000)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        searchImage(editable.toString())
                    }
                }

            }
        }
    }

    private fun searchImage(search: String,) = viewModelScope.launch {
        _uiStatus.value = Status.LOADING
        _imageListFromApi.value = listOf()
        when (val response = repository.searchArt(search)) {
            is NetworkResult.Success<*> -> {
                val data = response.result as ImageResponsePOJO
                val listArt = data.hits

                _imageListFromApi.value = listArt
            }
            is NetworkResult.Failure -> {
                _uiStatus.value = Status.ERROR
            }
        }
        _uiStatus.value = Status.STABLE
    }
}