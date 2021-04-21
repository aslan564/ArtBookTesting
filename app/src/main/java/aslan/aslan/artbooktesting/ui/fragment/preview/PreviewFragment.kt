package aslan.aslan.artbooktesting.ui.fragment.preview


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import aslan.aslan.artbooktesting.databinding.FragmentPreviewBinding
import aslan.aslan.artbooktesting.viewModel.preview.PreviewViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PreviewFragment @Inject constructor(
    glide: RequestManager
) : Fragment() {
    private var binding: FragmentPreviewBinding? = null
    private val viewModel by viewModels<PreviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPreviewBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    override fun onStart() {
        super.onStart()
        subscribeObserver()
    }


    private fun bindUI(): Unit = with(binding) {
        this?.lifecycleOwner = this@PreviewFragment
        this?.viewModelBinding = this@PreviewFragment.viewModel

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    private fun subscribeObserver(): Unit = with(viewModel) {
        arguments?.let {
            val art = PreviewFragmentArgs.fromBundle(it).StringSendArtOtherFragment
            art?.let { url ->
                getImageUrl(url = url.largeImageURL,url.name)
                Log.i(TAG, "subscribeObserver: $url")
            }
        }
    }

    companion object {
        private const val TAG = "PreviewFragment"
    }
}