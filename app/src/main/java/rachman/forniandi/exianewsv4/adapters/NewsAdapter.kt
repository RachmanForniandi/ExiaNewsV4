package rachman.forniandi.exianewsv4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.exianewsv4.databinding.ItemNewsBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.util.FormatUtil

class NewsAdapter(val articles:ArrayList<ArticleModel>,
                  val listener: OnNewsClickListener)
    : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    class NewsHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticleModel){
            binding.article = article
            binding.format = FormatUtil()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder = NewsHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = articles[position]
        holder.bind(news)
       /* holder.binding.title.text = news.title
        holder.binding.publishedAt.text = news.publishedAt
        Glide
            .with(holder.binding.image)
            .load(news.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(holder.binding.image)*/
        holder.itemView.setOnClickListener {
            listener.onClick(news)
        }

    }
    interface OnNewsClickListener {
        fun onClick(news: ArticleModel)
    }

    fun addNews(data:List<ArticleModel>){
        //articles.clear()
        articles.addAll(data)
        //notifyDataSetChanged()
        notifyItemRangeInserted((articles.size - data.size),data.size)
    }

    fun clear(){
        articles.clear()
        notifyDataSetChanged()
    }


}