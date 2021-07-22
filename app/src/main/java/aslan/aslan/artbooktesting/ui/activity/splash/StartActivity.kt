package aslan.aslan.artbooktesting.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import aslan.aslan.artbooktesting.databinding.ActivityStartBinding
import aslan.aslan.artbooktesting.ui.activity.main.MainActivity
import kotlinx.coroutines.delay

class StartActivity : AppCompatActivity() {


    private val binding by lazy { ActivityStartBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindUI()
    }


    private fun bindUI(): Unit = with(binding) {
        lifecycleScope.launchWhenCreated {
            delay(2000)
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
        }

    }

    companion object {
        private const val TAG = "StartActivity"
    }

}
