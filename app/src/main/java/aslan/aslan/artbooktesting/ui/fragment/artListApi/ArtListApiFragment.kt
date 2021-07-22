package aslan.aslan.artbooktesting.ui.fragment.artListApi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import aslan.aslan.artbooktesting.databinding.FragmentArtListApiBinding
import aslan.aslan.artbooktesting.roomDB.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.ui.fragment.artListApi.adapter.ArtApiAdapter
import aslan.aslan.artbooktesting.viewModel.artListFromApi.ArtFromApiViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "ArtListApiFragment"

@AndroidEntryPoint
class ArtListApiFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentArtListApiBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ArtFromApiViewModel>()
    private val navController by lazy { findNavController() }

    private val adapter by lazy {
        ArtApiAdapter(onClickImageApi = this::clickImage, glide)
    }

    private fun clickImage(imageResultPOJO: ImageResultPOJO) {
        Log.i("onClickImageApi", ": ${imageResultPOJO.largeImageURL}")
        val argsAction = ArtListApiFragmentDirections.artListApiToArtDetails(imageResultPOJO)
        navController.navigate(argsAction)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtListApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        observeArtListFromApi()
        configureRecyclerView()

    }

    private fun configureRecyclerView(): Unit = with(binding.recyclerViewListApi) {
        adapter = this@ArtListApiFragment.adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchImages()
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@ArtListApiFragment
        viewModelArt = this@ArtListApiFragment.viewModel

        ArtApiAdapter(onClickImageApi = {
            Log.d("TAG", "bindUI: ")
        }, glide)
    }

    private fun observeArtListFromApi(): Unit = with(viewModel) {
        imageListFromApi.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
                for (image in it) {
                    Log.d(TAG, "bindUI: $image")
                }
            }
        })
        searchImageBind(binding.editTextSearch)
    }


}