package rachman.forniandi.exianewsv4.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import rachman.forniandi.exianewsv4.R
import rachman.forniandi.exianewsv4.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment)

        binding.navView.setupWithNavController(navController)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)



    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit))
            .setMessage(getString(R.string.are_you_sure_do_you_want_to_exit))
            .setNegativeButton(getString(R.string.no), null)
            .setPositiveButton(getString(R.string.yes), object : DialogInterface.OnClickListener {
                override fun onClick(arg0: DialogInterface?, arg1: Int) {
                    super@HomeActivity.onBackPressed()

                }
            }).create().show()
    }

}