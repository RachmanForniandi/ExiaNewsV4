package rachman.forniandi.exianewsv4.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import rachman.forniandi.exianewsv4.R
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark,menu)
        val menuBookmark = menu.findItem(R.id.action_bookmark)
        menuBookmark.setOnMenuItemClickListener {
            Toast.makeText(this@DetailActivity,"add bookmark",Toast.LENGTH_SHORT).show()
            menuBookmark.setIcon(R.drawable.ic_check)
            true
        }
        return super.onCreateOptionsMenu(menu)
    }
}