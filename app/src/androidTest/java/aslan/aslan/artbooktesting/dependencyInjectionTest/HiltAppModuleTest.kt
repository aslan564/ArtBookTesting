package aslan.aslan.artbooktesting.dependencyInjectionTest

import android.content.Context
import androidx.room.Room
import aslan.aslan.artbooktesting.roomDB.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltAppModuleTest {

    @Singleton
    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoomDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, ArtDatabase::class.java).allowMainThreadQueries()
            .build()
}