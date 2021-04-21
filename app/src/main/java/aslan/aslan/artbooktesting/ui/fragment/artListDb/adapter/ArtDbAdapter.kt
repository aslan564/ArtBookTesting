package aslan.aslan.artbooktesting.ui.fragment.artListDb.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.entity.ArtDiffUtil
import com.bumptech.glide.RequestManager

class ArtDbAdapter(
    private val onClickImageApi: (Art) -> Unit,
    val glide: RequestManager
) : RecyclerView.Adapter<ArtViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(glide,arts[position],onClickImageApi)
    }

    override fun getItemCount(): Int {
        return arts.size
    }

    private val recyclerDifferConfig = AsyncListDiffer(this, ArtDiffUtil)

    var arts: List<Art>
        get() = recyclerDifferConfig.currentList
        set(value) = recyclerDifferConfig.submitList(value)

}