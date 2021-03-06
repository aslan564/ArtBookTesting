 package aslan.aslan.artbooktesting.ui.fragment.artListDb

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import aslan.aslan.artbooktesting.databinding.FragmentArtListBinding
import aslan.aslan.artbooktesting.ui.fragment.artListDb.adapter.ArtDbAdapter
import aslan.aslan.artbooktesting.viewModel.artListFromDb.ArtDbViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ArtListFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {
    companion object {
        private const val TAG = "ArtListFragment"

    }

    private var _binding: FragmentArtListBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy { findNavController() }
    private val viewModel by viewModels<ArtDbViewModel>()
    private val adapter by lazy {
        ArtDbAdapter(onClickImageApi = {
            navController.navigate(ArtListFragmentDirections.navigateArtListToPreview(it))
        }, glide)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtListBinding.inflate(inflater, container, false)
        Log.i(TAG, "onCreateView: called")
        subscribeObserverData()

        setHasOptionsMenu(true)
        return binding.root
    }

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPositions = viewHolder.adapterPosition
            val selectedArt = adapter.currentList[layoutPositions]
            selectedArt.let {
                viewModel.deleteArt(it)
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        Log.i(TAG, "onViewCreated: called")
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@ArtListFragment
        viewModelArt = viewModel
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(recyclerListArts)
        recyclerListArts.apply {
            adapter = this@ArtListFragment.adapter
        }

        fabActionButton.setOnClickListener {
            val action = ArtListFragmentDirections.navigateArtListToArtDetails(null)

            navController.navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null
        Log.i(TAG, "onDestroyView: called")
        super.onDestroyView()
    }


   /* @OnLifecycleEvent(Lifecycle.Event.ON_START)*/
    private fun subscribeObserverData(): Unit = with(viewModel) {
        art.observe(viewLifecycleOwner,{
            it?.let {
                this@ArtListFragment.adapter.submitList(it)
            }
        })
    }
}

/*
*   I am trying to reach my short and long term goals consistently.
    I calmly handle customer complaints and empathy.
    I proactively participated in new lessons and experience programs and helped create new applications.
    I am ready to work as a mobile android developer in more projects and experience programs or any company and I am sure that I will do very well.
* */