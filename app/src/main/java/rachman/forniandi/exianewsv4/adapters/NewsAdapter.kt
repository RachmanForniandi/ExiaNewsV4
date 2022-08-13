package rachman.forniandi.exianewsv4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.exianewsv4.databinding.ItemHeadlineBinding
import rachman.forniandi.exianewsv4.databinding.ItemNewsBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.util.FormatUtil

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
            binding.article = article
            binding.format = FormatUtil()
        }
    }

    class HeadLineHolder(val binding: ItemHeadlineBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticleModel){
            binding.article = article
            binding.format = FormatUtil()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType== HEADLINES){
            HeadLineHolder(
                ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
        }else {NewsHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
            }
    }

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = articles[position]
        if (VIEW_TYPE== HEADLINES)
            (holder as HeadLineHolder).bind(article)
        else(holder as NewsHolder).bind(article)
       /* holder.binding.title.text = article.title
        holder.binding.publishedAt.text = article.publishedAt
        Glide
            .with(holder.binding.image)
            .load(article.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(holder.binding.image)*/
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