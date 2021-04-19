package aslan.aslan.artbooktesting.ui.fragment.artListApi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.pojo.ArtApiDiffUtil
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.util.Status
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class ArtApiAdapter (
    private val onClickImageApi: (ImageResultPOJO) -> Unit,
    val glide: RequestManager
) : ListAdapter<ImageResultPOJO, ArtApiViewHolder>(ArtApiDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtApiViewHolder {
        return ArtApiViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtApiViewHolder, position: Int) {
        holder.bind(glide,getItem(position), onClickImageApi)
    }
}