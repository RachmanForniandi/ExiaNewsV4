package rachman.forniandi.exianewsv4.ui.bookmark

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.adapters.NewsAdapter
import rachman.forniandi.exianewsv4.databinding.CustomToolbarBinding
import rachman.forniandi.exianewsv4.databinding.FragmentBookmarkBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.ui.detail.DetailActivity

val bookMarkModule = module {
    factory { BookmarkFragment() }
}
class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: BookmarkViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindingToolbar.txtTitle.text = viewModel.title
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel =viewModel
        bindingToolbar.title = viewModel.title

        NewsAdapter.VIEW_TYPE=2
        binding.listBookmark.adapter = newsAdapter

        viewModel.articles.observe(viewLifecycleOwner,{
            newsAdapter.clear()
            newsAdapter.addNews(it)
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(),object : NewsAdapter.OnNewsClickListener{
            override fun onClick(news: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("detail", news)
                )
            }
        })
    }
}