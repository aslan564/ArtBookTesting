package aslan.aslan.artbooktesting.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import aslan.aslan.artbooktesting.R
import aslan.aslan.artbooktesting.databinding.ActivityMainBinding
import aslan.aslan.artbooktesting.ui.fragment.artListApi.ArtListApiFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    @Inject
    lateinit var fragmentFactory: ArtListApiFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory=fragmentFactory
        _binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

    }
}