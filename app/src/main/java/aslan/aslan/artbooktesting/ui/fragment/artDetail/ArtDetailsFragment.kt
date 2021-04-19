package aslan.aslan.artbooktesting.ui.fragment.artDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import javax.inject.Inject
import androidx.navigation.fragment.findNavController
import aslan.aslan.artbooktesting.databinding.FragmentArtDetailsBinding
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.viewModel.artDetails.ArtDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class ArtDetailsFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {

    private val TAG = "ArtDetailsFragment"
    private var _binding: FragmentArtDetailsBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy { findNavController() }
    private val viewModel by viewModels<ArtDetailsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@ArtDetailsFragment

       /* val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
*/
        arguments?.let {
            val artFromApi = ArtDetailsFragmentArgs.fromBundle(it).StringSendArtOtherFragment
            artFromApi?.let {
                glide.load(artFromApi.largeImageURL).into(imageViewArt)
            }
        }

        buttonSave.setOnClickListener {
            val name = editTextArtName.text.toString()
            val year = editTextArtYear.text.toString()
            val artistName = editTextArtistName.text.toString()
            if (name.isEmpty() || year.isEmpty() || artistName.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Name, Year, Artist Name must be",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                val yearInt = try {
                    year.toInt()
                } catch (ex: Exception) {
                    Toast.makeText(requireContext(), "Year should be number", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                arguments?.let { bundle ->
                    val artFromApi = ArtDetailsFragmentArgs.fromBundle(bundle).StringSendArtOtherFragment
                    artFromApi?.let {
                        viewModel.addImageToDb(it, name, yearInt, artistName) {
                            navController.navigate(ArtDetailsFragmentDirections.artDetailsFragmentToAddArtFragment())
                        }
                    }
                }
            }

        }
        imageViewArt.setOnClickListener {
            navController.navigate(ArtDetailsFragmentDirections.artDetailsToArtListApi())
        }
    }
}