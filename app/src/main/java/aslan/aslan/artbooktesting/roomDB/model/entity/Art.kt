package aslan.aslan.artbooktesting.roomDB.model.entity

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "arts")
data class Art(
    var name:String,
    var userId:Int,
    var artistName:String,
    var type:String,
    var year:Int,
    var previewURL:String,
    var largeImageURL:String,
    @PrimaryKey(autoGenerate = false)
    var id:Long

):Parcelable

class ArtDiffUtil() : DiffUtil.ItemCallback<Art>() {
    override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem.id==newItem.id
    }
}
