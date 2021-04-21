package aslan.aslan.artbooktesting.viewModel

import aslan.aslan.artbooktesting.repository.ImageRepositoryTestImpl
import aslan.aslan.artbooktesting.viewModel.artListFromApi.ArtFromApiViewModel
import org.junit.Before
import org.junit.Test

class ArtViewModelTest {

    private lateinit var viewModelTest: ArtFromApiViewModel

    @Before
    fun setup() {
        viewModelTest = ArtFromApiViewModel(ImageRepositoryTestImpl())
    }

    @Test
    fun `insert art without year returns error`(){
        TODO("Not yet implemented")
    }

    @Test
    fun `insert art without name returns error`() {
        TODO("Not yet implemented")
    }
}