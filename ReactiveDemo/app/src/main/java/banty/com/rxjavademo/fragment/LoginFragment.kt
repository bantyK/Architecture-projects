package banty.com.rxjavademo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import banty.com.rxjavademo.R
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers


class LoginFragment : Fragment() {

    internal lateinit var emailEditText: EditText
    internal lateinit var passwordEditText: EditText
    internal lateinit var loginButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_login_fragment, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        emailEditText = view.findViewById(R.id.edit_email)
        passwordEditText = view.findViewById(R.id.edit_password)
        loginButton = view.findViewById(R.id.btn_login)
    }

    override fun onResume() {
        super.onResume()
        login()
    }

    private fun login() {
        Observable.create({ subscriber: Subscriber<in LoginRequest>? ->
            loginButton.setOnClickListener({
                subscriber?.onNext(LoginRequest(emailEditText.text.toString(), passwordEditText.text.toString()))
            })
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe { loginRequest -> Log.d("Login", "Email using ${loginRequest.email} and ${loginRequest.password}") }
    }

    data class LoginRequest(val email: String, val password: String)
}
