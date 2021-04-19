package aslan.aslan.artbooktesting.db.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    var name:String,
    var userId:Int,
    var artistName:String,
    var type:String,
    var year:Int,
    @PrimaryKey(autoGenerate = false)
    var id:Long= Long.MIN_VALUE

)
