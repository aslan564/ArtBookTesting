package aslan.aslan.artbooktesting.viewModel.preview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private var _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image

    private var _imageName = MutableLiveData<String>()
    val imageName: LiveData<String>
        get() = _imageName

    fun getImageUrl(url:String,name:String)=viewModelScope.launch {
        _image.postValue(url)
        _imageName.postValue(name)
    }
}