package rachman.forniandi.exianewsv4.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.adapters.CategoryAdapter
import rachman.forniandi.exianewsv4.adapters.NewsAdapter
import rachman.forniandi.exianewsv4.databinding.CustomToolbarBinding
import rachman.forniandi.exianewsv4.databinding.FragmentHomeBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.source.news.CategoryModel
import rachman.forniandi.exianewsv4.ui.detail.DetailActivity
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for th
        // is fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindingToolbar.txtTitle.text = viewModel.title
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        bindingToolbar.title = viewModel.title
        bindingToolbar.container.inflateMenu(R.menu.menu_search)
        val menu = binding.toolbar.container.menu
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                firstLoad()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.query = it
                }
                return true
            }
        })
        //Timber.e(viewModel.categories.toString())

        binding.listCategory.adapter = categoryAdapter


        viewModel.category.observe(viewLifecycleOwner,{
            //Timber.e(it)
            //viewModel.fetchNewsData()
            firstLoad()
        })

        binding.listNews.adapter = newsAdapter
        viewModel.news.observe(viewLifecycleOwner,{
            Timber.e(it.articles.toString())
            /*binding.imgAlert.visibility = if (it.articles.isEmpty())View.VISIBLE else View.GONE
            binding.txtAlert.visibility = if (it.articles.isEmpty())View.VISIBLE else View.GONE*/
            newsAdapter.addNews(it.articles)
        })
        viewModel.message.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun firstLoad(){
        binding.scroll.scrollTo(0,0)
        viewModel.fetchNewsData()
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories,object :CategoryAdapter.OnAdapterListener{
            override fun onClick(category: CategoryModel) {
                Timber.e(category.id)
                viewModel.category.postValue(category.id)
            }
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(),object :NewsAdapter.OnNewsClickListener{
            override fun onClick(news: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("detail", news)
                )
            }
        })
    }



}