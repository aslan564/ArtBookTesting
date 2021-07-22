package aslan.aslan.artbooktesting.roomDB.model.pojo

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResultPOJO(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth : Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL : String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth:Int,
    val tags: String,
    val type: String,
    val user: String,
    @SerializedName("user_id")
    val userId : Int,
    val userImageURL: String,
    val views : Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
):Parcelable

class ArtApiDiffUtil : DiffUtil.ItemCallback<ImageResultPOJO>() {
    override fun areItemsTheSame(oldItem: ImageResultPOJO, newItem: ImageResultPOJO): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ImageResultPOJO, newItem: ImageResultPOJO): Boolean {
        return oldItem.id==newItem.id
    }
}
