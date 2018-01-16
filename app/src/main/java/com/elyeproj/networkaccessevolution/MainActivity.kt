package com.elyeproj.networkaccessevolution

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private val networkAccessDirect = NetworkAccessDirect(this)
    private val networkAccessThread = NetworkAccessThread(this)
    private val networkAccessAsync = NetworkAccessAsync(this)
    private val networkAccessIntentService = NetworkAccessIntentService(this)
    private val networkAccessRxJava = NetworkAccessRxJava(this)
    private val networkAccessRxJavaKotlin = NetworkAccessRxJavaKotlin(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkAccessIntentService.registerReceiver()

        btn_search_direct.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchDirect(edit_search.text.toString())
            }
        }

        btn_search_thread.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchThread(edit_search.text.toString())
            }
        }

        btn_search_async.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchAsync(edit_search.text.toString())
            }
        }

        btn_search_intent_service.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchIntentService(edit_search.text.toString())
            }
        }

        btn_search_rxjava.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchRxJava(edit_search.text.toString())
            }
        }

        btn_search_rxjava_kotlin.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearchRxJavaKotlin(edit_search.text.toString())
            }
        }

    }

    private fun beginSearchDirect(queryString: String) {
        networkAccessDirect.fetchData(queryString)
    }

    private fun beginSearchThread(queryString: String) {
        networkAccessThread.fetchData(queryString)
    }

    private fun beginSearchAsync(queryString: String) {
        networkAccessAsync.fetchData(queryString)
    }

    private fun beginSearchIntentService(queryString: String) {
        networkAccessIntentService.fetchData(queryString)
    }

    private fun beginSearchRxJava(queryString: String) {
        networkAccessRxJava.fetchData(queryString)
    }

    private fun beginSearchRxJavaKotlin(queryString: String) {
        networkAccessRxJavaKotlin.fetchData(queryString)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkAccessDirect.terminate()
        networkAccessThread.terminate()
        networkAccessAsync.terminate()
        networkAccessIntentService.terminate()
        networkAccessRxJava.terminate()
    }

    override fun updateScreen(result: String) {
        txt_search_result.text = result
    }

    override fun unregisterIntentServiceReceiver(receiver: BroadcastReceiver) {
        unregisterReceiver(receiver)
    }

    override fun registerIntentServiceReceiver(receiver: BroadcastReceiver, filter: IntentFilter) {
        this.registerReceiver(receiver, filter)
    }

    override fun startIntentService(intent: Intent) {
        startService(intent)
    }
}
