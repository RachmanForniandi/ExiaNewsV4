package rachman.forniandi.exianewsv4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.adapters.CategoryAdapter
import rachman.forniandi.exianewsv4.databinding.CustomToolbarBinding
import rachman.forniandi.exianewsv4.databinding.FragmentHomeBinding
import rachman.forniandi.exianewsv4.source.news.CategoryModel
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
        bindingToolbar.txtTitle.text = viewModel.title
        Timber.e(viewModel.categories.toString())
        binding.listCategory.adapter = categoryAdapter
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories,object :CategoryAdapter.OnAdapterListener{
            override fun onClick(category: CategoryModel) {
                Timber.e(category.id)

            }
        })
    }



}