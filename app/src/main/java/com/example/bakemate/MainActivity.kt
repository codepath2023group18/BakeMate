package com.example.bakemate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRedditData()
    }

    private fun getRedditData() {
        val client = AsyncHttpClient()

        client["https://api.reddit.com/r/baking/new?limit=10", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                // Access a JSON object response with `json.jsonObject`
                 Log.d("DEBUG OBJECT", json.jsonObject.toString())
                //Log.d("DEBUG RESPONSE", json.jsonArray.toString())
                // Log.d("DEBUG RESPONSE", "$headers")
                val rateLimitUsed = headers["x-ratelimit-used"]
                val rateLimitRemaining = headers["x-ratelimit-remaining"]
                val rateLimitReset = headers["x-ratelimit-reset"]
                Log.d(
                    "DEBUG RESPONSE",
                    " rate used: $rateLimitUsed |rate limit remaining: $rateLimitRemaining" +
                            "| rate limit reset (seconds) $rateLimitReset"
                )
                Log.d("DEBUG RESPONSE", "$json")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
            }
        }]
    }
}