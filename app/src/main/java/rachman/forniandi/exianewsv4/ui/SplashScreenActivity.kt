package rachman.forniandi.exianewsv4.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rachman.forniandi.exianewsv4.databinding.ActivitySplashScreenBinding
import rachman.forniandi.exianewsv4.ui.home.HomeActivity
import java.util.*
import kotlin.concurrent.schedule


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timer("splashGone", true).schedule(3000) {
            val intent = Intent(this@SplashScreenActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
