package rachman.forniandi.exianewsv4.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.databinding.ItemCategoryBinding
import rachman.forniandi.exianewsv4.source.news.CategoryModel

class CategoryAdapter(val categories:List<CategoryModel>,
                      val onClickListener: OnAdapterListener)
    : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val items = arrayListOf<TextView>()

    class CategoryHolder(val binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder = CategoryHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add(holder.binding.category)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(category)
            setColor(holder.binding.category)
        }
        setColor(items[0])
    }
    interface OnAdapterListener {
        fun onClick(category:CategoryModel)
    }

    private fun setColor(textView: TextView){
        items.forEach{
            it.setBackgroundColor(R.color.white)
        }
        textView.setBackgroundColor(android.R.color.darker_gray)
    }
}