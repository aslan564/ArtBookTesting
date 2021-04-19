package aslan.aslan.artbooktesting.ui.fragment.artListDb.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.entity.ArtDiffUtil
import com.bumptech.glide.RequestManager

class ArtDbAdapter ( private val onClickImageApi: (Art) -> Unit,
val glide: RequestManager
) : ListAdapter<Art, ArtViewHolder>(ArtDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(glide,getItem(position), onClickImageApi)
    }
}