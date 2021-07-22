package aslan.aslan.artbooktesting.ui.fragment.artListDb.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.roomDB.model.entity.ArtDiffUtil
import com.bumptech.glide.RequestManager

class ArtDbAdapter(
    private val onClickImageApi: (Art) -> Unit,
    val glide: RequestManager
) : ListAdapter<Art,ArtViewHolder>(ArtDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        return ArtViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        holder.bind(glide,getItem(position),onClickImageApi)
    }



}
/*
class ArtClickListener(val clickListener: (Art)->Unit){
    fun onClickArt(art: Art)=clickListener(art)

     override fun getItemCount(): Int {
        return arts.size
    }

    private val recyclerDifferConfig = AsyncListDiffer(this, ArtDiffUtil)

    var arts: List<Art>
        get() = recyclerDifferConfig.currentList
        set(value) = recyclerDifferConfig.submitList(value)
}*/
