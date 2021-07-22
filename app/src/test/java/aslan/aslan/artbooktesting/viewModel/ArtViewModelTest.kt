package aslan.aslan.artbooktesting.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import aslan.aslan.artbooktesting.MainCoroutineRule
import aslan.aslan.artbooktesting.getOrAwaitValueTest
import aslan.aslan.artbooktesting.repository.ImageRepositoryTestImpl
import aslan.aslan.artbooktesting.util.Status
import aslan.aslan.artbooktesting.viewModel.artListFromApi.ArtFromApiViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {


    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    
    @get:Rule
    var mainCoroutineRule=MainCoroutineRule()

    private lateinit var viewModelTest: ArtFromApiViewModel

    @Before
    fun setup() {
        viewModelTest = ArtFromApiViewModel(ImageRepositoryTestImpl())
    }

    @Test
    fun `insert art without year returns error`() {
       val data=viewModelTest.imageListFromApi.getOrAwaitValueTest()
        assertThat(data).isEqualTo(Status.STABLE)
    }

    @Test
    fun `insert art without name returns error`() {
        TODO("Not yet implemented")
    }
}