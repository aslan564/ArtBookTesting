package aslan.aslan.artbooktesting.ui.fragment.artListApi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import aslan.aslan.artbooktesting.ui.fragment.artDetail.ArtDetailsFragment
import aslan.aslan.artbooktesting.ui.fragment.artListApi.adapter.ArtApiAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtListApiFragmentFactory @Inject constructor(
    private val glide: RequestManager
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ArtListApiFragment::class.java.name -> {
                ArtListApiFragment(glide)
            }
            ArtDetailsFragment::class.java.name -> {
                ArtDetailsFragment(glide)
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}