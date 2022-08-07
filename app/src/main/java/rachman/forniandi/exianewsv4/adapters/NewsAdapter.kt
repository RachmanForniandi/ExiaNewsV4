package rachman.forniandi.exianewsv4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.databinding.ItemNewsBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.source.NewsModel
import rachman.forniandi.exianewsv4.source.news.CategoryModel

class NewsAdapter(val articles:ArrayList<ArticleModel>,
                  val onClickListener: OnNewsClickListener)
    : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private val items = arrayListOf<TextView>()

    class NewsHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder = NewsHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = articles[position]
        holder.binding.title.text = news.title
        holder.binding.publishedAt.text = news.publishedAt
        Glide
            .with(holder.binding.image)
            .load(news.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.place_holder)
            .into(holder.binding.image)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(news)
        }

    }
    interface OnNewsClickListener {
        fun onClick(news: ArticleModel)
    }
    fun addNews(data:List<ArticleModel>){
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }


}