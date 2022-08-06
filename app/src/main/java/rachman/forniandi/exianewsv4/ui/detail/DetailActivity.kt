package rachman.forniandi.exianewsv4.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rachman.forniandi.exianewsv4.databinding.ActivityDetailBinding
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {ActivityDetailBinding.inflate(layoutInflater)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}