package rachman.forniandi.exianewsv4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import loadImage
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.databinding.ItemHeadlineBinding
import rachman.forniandi.exianewsv4.databinding.ItemNewsBinding
import rachman.forniandi.exianewsv4.source.ArticleModel

private const val HEADLINES = 1
private const val NEWS = 2

class NewsAdapter(val articles:ArrayList<ArticleModel>,
                  val listener: OnNewsClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        var VIEW_TYPE= 1
    }

    class NewsHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticleModel){
            binding.title.text = article.title ?: ""
            binding.publishedAt.text = article.publishedAt
            loadImage(binding.image, article.urlToImage)
        }
    }

    class HeadLineHolder(val binding: ItemHeadlineBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticleModel){
            binding.title.text = article.title ?: ""
            binding.publishedAt.text = article.publishedAt
            loadImage(binding.image, article.urlToImage)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType== HEADLINES){
            HeadLineHolder(
                ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }else {
            NewsHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        if (VIEW_TYPE== HEADLINES)
            (holder as HeadLineHolder).bind(article)
        else(holder as NewsHolder).bind(article)
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }

    }
    interface OnNewsClickListener {
        fun onClick(news: ArticleModel)
    }

    fun addNews(data:List<ArticleModel>){
        articles.addAll(data)
        notifyItemRangeInserted((articles.size - data.size),data.size)
    }

    fun clear(){
        articles.clear()
        notifyDataSetChanged()
    }


}
