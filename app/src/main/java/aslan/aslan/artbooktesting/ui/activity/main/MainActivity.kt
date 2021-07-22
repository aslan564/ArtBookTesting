package aslan.aslan.artbooktesting.ui.activity.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import aslan.aslan.artbooktesting.R
import aslan.aslan.artbooktesting.databinding.ActivityMainBinding
import aslan.aslan.artbooktesting.ui.fragment.artListApi.ArtListApiFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private lateinit var navDrawer: DrawerLayout

    @Inject
    lateinit var fragmentFactory: ArtListApiFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navDrawer = _binding.drawerLayout
        val navController = this.findNavController(R.id.fragment_container_view)
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController, navDrawer)
        NavigationUI.setupWithNavController(_binding.navigationView, navController)
        _binding.navigationView.setNavigationItemSelectedListener(this::selectedNavItem)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val drawer = ActionBarDrawerToggle(
            this,
            navDrawer,
            R.string.navigation_drawer_left,
            R.string.navigation_drawer_right
        )

        navDrawer.addDrawerListener(drawer)
    }

    private fun selectedNavItem(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_about->{
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            }
            R.id.nav_rule->{
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this@MainActivity.findNavController(R.id.fragment_container_view)
        return NavigationUI.navigateUp(navController, navDrawer)
    }

    override fun onBackPressed() {
        if (navDrawer.isVisible && navDrawer.isOpen) {
            navDrawer.close()
            return
        }
        super.onBackPressed()
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        return super.onCreateOptionsMenu(menu)
    }*/

}