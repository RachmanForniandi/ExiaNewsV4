package rachman.forniandi.exianewsv4.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.ui.home.HomeActivity
import rachman.forniandi.exianewsv4.ui.home.HomeFragment
import java.util.*
import kotlin.concurrent.schedule


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        Timer("splashGone", true).schedule(3000) {
            val intent = Intent(this@SplashScreenActivity,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}