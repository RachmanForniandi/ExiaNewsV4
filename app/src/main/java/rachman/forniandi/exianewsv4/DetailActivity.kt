package rachman.forniandi.exianewsv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rachman.forniandi.exianewsv4.databinding.ActivityDetailBinding
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private val binding = ActivityDetailBinding. inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        setContentView(binding.root)
    }
}