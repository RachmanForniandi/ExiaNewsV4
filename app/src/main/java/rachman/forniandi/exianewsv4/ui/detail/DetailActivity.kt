package rachman.forniandi.exianewsv4.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.databinding.ActivityDetailBinding
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding
import rachman.forniandi.exianewsv4.databinding.CustomToolbarBinding
import rachman.forniandi.exianewsv4.source.ArticleModel
val detailModule = module {
    factory { DetailActivity() }
}
class DetailActivity : AppCompatActivity() {

    private val binding by lazy {ActivityDetailBinding.inflate(layoutInflater)  }
    private lateinit var bindingToolbar:CustomToolbarBinding
    private val viewModel:DetailViewModel by viewModel()
    private val detail by lazy {
        intent.getSerializableExtra("detail") as ArticleModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingToolbar = binding.toolbar
        setSupportActionBar(bindingToolbar.container)
        supportActionBar!!.apply {
            title=""
            setDisplayHomeAsUpEnabled(true)
        }

        detail.let {
            viewModel.find(it)
            val web = binding.webViewDisplay
            web.loadUrl(it.url!!)
            web.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.pgTop.visibility = View.GONE
                }

            }
            val setting = binding.webViewDisplay.settings
            setting.javaScriptCanOpenWindowsAutomatically = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark,menu)
        val menuBookmark = menu.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            viewModel.bookmark(detail)
            Toast.makeText(this@DetailActivity,"add bookmark",Toast.LENGTH_SHORT).show()

            true
        }
        viewModel.isBookmark.observe(this,{
            if (it ==0)menuBookmark.setIcon(R.drawable.ic_add)
            else menuBookmark.setIcon(R.drawable.ic_check)
        })
        return super.onCreateOptionsMenu(menu)
    }
}