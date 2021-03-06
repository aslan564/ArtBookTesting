package aslan.aslan.artbooktesting.ui.fragment.artListDb.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aslan.aslan.artbooktesting.databinding.LayoutItemArtBinding
import aslan.aslan.artbooktesting.roomDB.model.entity.Art
import aslan.aslan.artbooktesting.util.downloadGlide
import com.bumptech.glide.RequestManager

class ArtViewHolder private constructor(
    private val binding: LayoutItemArtBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        glide: RequestManager?,
        art: Art,
        onclickImagePOJO: (Art) -> Unit,
    ): Unit = with(binding) {
        artModel=art
        binding.executePendingBindings()

        /*
         glide?.load(art.previewURL)?.into(imageViewArt)
        imageViewArt.downloadGlide(imageResultPOJO.previewURL){
            if (it) {
                progressBar.visibility=GONE
            }
        }
        */
        root.setOnClickListener {
            onclickImagePOJO(art)
        }

    }

    companion object {
        fun from(group: ViewGroup): ArtViewHolder {
            val inflater = LayoutInflater.from(group.context)
            val binding = LayoutItemArtBinding.inflate(inflater, group, false)
            return ArtViewHolder(binding)
        }
    }

}