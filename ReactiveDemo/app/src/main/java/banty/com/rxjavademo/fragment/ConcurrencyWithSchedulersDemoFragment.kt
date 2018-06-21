package banty.com.rxjavademo.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import banty.com.rxjavademo.R
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by Banty on 19/06/18.
 */
class ConcurrencyWithSchedulersDemoFragment : Fragment() {

    val TAG = "ConcurrencySchdl"

    internal var progress: ProgressBar? = null

    internal var logsList: ListView? = null

    internal var button: Button? = null

    private var adapter: LogAdapter? = null


    private var logs: MutableList<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("onCreateView : view created")
        val view = inflater.inflate(R.layout.fragment_container, container, false)
        initViews(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLogger()
    }

    private fun initViews(view: View) {
        progress = view.findViewById(R.id.progress_operation_running)
        logsList = view.findViewById(R.id.list_threading_log)
        button = view.findViewById(R.id.btn_start_operation)
        button?.setOnClickListener { startLongOperation() }
    }

    private fun startLongOperation() {
        log("Long op button clicked")
        progress?.visibility = View.VISIBLE

        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<Boolean> {
        return object : Observer<Boolean> {
            override fun onError(e: Throwable?) {
                log("Error in observer")
                progress?.visibility = View.GONE
            }

            override fun onNext(bool: Boolean?) {
                log(String.format("onNext with return value \"%b\"", bool))
            }

            override fun onCompleted() {
                log("On Complete")
                progress?.visibility = View.GONE
            }

        }
    }

    private fun getObservable(): Observable<Boolean> {
        return Observable.just(true).map { aBoolean ->
            log("Within Observable")
            doSomeLongOperationThatBlocksCurrentThread()
            aBoolean
        }

    }

    private fun doSomeLongOperationThatBlocksCurrentThread() {
        log("Perform some long operation")
        Thread.sleep(3000)
    }

    private fun log(logMsg: String) {
        Log.d(TAG, logMsg)
        if (currentlyOnMainThread()) {
            logs?.add(0, "$logMsg (ON MAIN THREAD)")
//            adapter?.clear()
            adapter?.addAll(logs)

        } else {
            logs?.add(0, "$logMsg (NOT ON MAIN THREAD)")
            Handler(Looper.getMainLooper()).post {
//                adapter?.clear()
                adapter?.addAll(logs)

            }
        }
    }

    private fun setupLogger() {
        logs = ArrayList()
        adapter = LogAdapter(context, logs as ArrayList<String>)
        logsList?.adapter = adapter
    }

    private fun currentlyOnMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }


    private inner class LogAdapter(context: Context?, logs: List<String>) : ArrayAdapter<String>(context, R.layout.item_log, R.id.item_log, logs)


}