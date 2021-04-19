package aslan.aslan.artbooktesting.db

import androidx.room.Database
import androidx.room.RoomDatabase
import aslan.aslan.artbooktesting.db.model.entity.Art

@Database(entities = [Art::class],version = 1,exportSchema = false)
abstract class ArtDatabase: RoomDatabase() {
    abstract fun artDao():ArtDao
}