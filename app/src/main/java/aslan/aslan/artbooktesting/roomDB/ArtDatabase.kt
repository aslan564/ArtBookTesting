package aslan.aslan.artbooktesting.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import aslan.aslan.artbooktesting.roomDB.model.entity.Art

@Database(entities = [Art::class], version = 1, exportSchema = false)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao

   /* abstract val artDatabaseDao: ArtDao

    companion object {
        @Volatile
        private var INSTANCE: ArtDatabase? = null

        fun getInstance(context: Context): ArtDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArtDatabase::class.java,
                        "ArtDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }*/
}