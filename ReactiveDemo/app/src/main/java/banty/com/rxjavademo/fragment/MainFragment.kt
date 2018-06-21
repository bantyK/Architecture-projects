package banty.com.rxjavademo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import banty.com.rxjavademo.R

/**
 * Created by Banty on 19/06/18.
 */
class MainFragment : Fragment(), View.OnClickListener {

    val TAG = "MainFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.btn_demo_schedulers).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_login_demo).setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_demo_schedulers -> {
                log("Demo schedulers")
                startFragment(ConcurrencyWithSchedulersDemoFragment())
            }

            R.id.btn_login_demo -> {
                log("Login demo")
                startFragment(LoginFragment())
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(android.R.id.content, fragment)
                ?.commit()
    }

    private fun log(logMsg: String) {
        Log.d(TAG, logMsg)
    }
}