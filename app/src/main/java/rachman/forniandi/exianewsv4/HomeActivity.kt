package rachman.forniandi.exianewsv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        setContentView(binding.root)

    }
}