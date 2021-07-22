package aslan.aslan.artbooktesting.roomDBTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import aslan.aslan.artbooktesting.convertLiveDataToSimpleList
import aslan.aslan.artbooktesting.roomDB.ArtDao
import aslan.aslan.artbooktesting.roomDB.ArtDatabase
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var databaseImpl: ArtDatabase
    private lateinit var dao: ArtDao

    @Before
    fun setUp() {
        /* databaseImpl = Room.inMemoryDatabaseBuilder(
             ApplicationProvider.getApplicationContext(),
             ArtDatabase::class.java
         ).allowMainThreadQueries().build()
 */
        hiltRule.inject()
        dao = databaseImpl.artDao()
    }

    @After
    fun tearDown() {
        databaseImpl.close()
    }

    @Test
    fun addImageToDatabase() = runBlockingTest {
        val art = Art(
            "Aslan Aslanov",
            54652,
            "Aslanov Aslan",
            "image",
            1965,
            "wwww.aslanovaslan.com",
            "wwww.aslanovaslan.com",
            5646L
        )

        dao.insert(art)
        val result = dao.getArtListFromDb().convertLiveDataToSimpleList()
        assertThat(result).contains(art)

    }

    @Test
    fun deleteImageFromDB() = runBlockingTest {
        val art = Art(
            "Aslan Aslanov",
            54652,
            "Aslanov Aslan",
            "image",
            1965,
            "wwww.aslanovaslan.com",
            "wwww.aslanovaslan.com",
            5646L
        )

        dao.insert(art)
        dao.deleteArt(art)
        val result = dao.getArtListFromDb().convertLiveDataToSimpleList()
        assertThat(result).doesNotContain(art)

    }

}