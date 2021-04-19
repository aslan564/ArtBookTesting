package aslan.aslan.artbooktesting.ui.fragment.artListDb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import aslan.aslan.artbooktesting.R
import aslan.aslan.artbooktesting.databinding.FragmentArtListBinding
import aslan.aslan.artbooktesting.repository.ImageRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtListFragment @Inject constructor() : Fragment() {
    private var _binding: FragmentArtListBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
    }

    private fun bindUI(): Unit = with(binding) {
        fabActionButton.setOnClickListener {
            val action = ArtListFragmentDirections.navigateArtListToArtDetails(null)
            navController.navigate(action)
        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}