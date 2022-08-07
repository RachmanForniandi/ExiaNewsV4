package rachman.forniandi.exianewsv4.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import rachman.forniandi.exianewsv4.databinding.ActivityDetailBinding
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding
import rachman.forniandi.exianewsv4.databinding.CustomToolbarBinding
import rachman.forniandi.exianewsv4.source.ArticleModel

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {ActivityDetailBinding.inflate(layoutInflater)  }
    private lateinit var bindingToolbar:CustomToolbarBinding
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
}