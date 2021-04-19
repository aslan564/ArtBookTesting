package aslan.aslan.artbooktesting.ui.fragment.artListApi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aslan.aslan.artbooktesting.databinding.LayoutItemArtApiBinding
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import com.bumptech.glide.RequestManager

class ArtApiViewHolder(
    private val binding: LayoutItemArtApiBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        glide: RequestManager?,
        imageResultPOJO: ImageResultPOJO,
        onclickImagePOJO: (ImageResultPOJO) -> Unit,
    ): Unit = with(binding) {
        //imageApi = imageResultPOJO
        glide?.load(imageResultPOJO.previewURL)?.into(imageViewArt)
        root.setOnClickListener {
            onclickImagePOJO(imageResultPOJO)
        }

    }

    companion object {
        fun from(group: ViewGroup): ArtApiViewHolder {
            val inflater = LayoutInflater.from(group.context)
            val binding = LayoutItemArtApiBinding.inflate(inflater, group, false)
            return ArtApiViewHolder(binding)
        }
    }

}