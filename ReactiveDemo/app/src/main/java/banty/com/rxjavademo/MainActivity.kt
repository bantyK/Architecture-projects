package banty.com.rxjavademo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import banty.com.rxjavademo.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MainFragment())
                .commit()
    }

}
