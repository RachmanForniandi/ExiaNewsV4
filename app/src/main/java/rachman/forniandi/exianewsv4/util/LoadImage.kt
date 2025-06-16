package rachman.forniandi.exianewsv4.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import rachman.forniandi.exianewsv4.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView,urlString: String?){
    urlString.let {
        Glide
            .with(imageView)
            .load(urlString)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.place_holder)
            .into(imageView)
    }
}