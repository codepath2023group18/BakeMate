package com.example.bakemate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlinx.serialization.*
import kotlinx.serialization.json.*


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
                Log.d("OBJECT", json.jsonObject.toString())
                //Log.d("DEBUG RESPONSE", json.jsonArray.toString())
                // Log.d("DEBUG RESPONSE", "$headers")
                val rateLimitUsed = headers["x-ratelimit-used"]
                val rateLimitRemaining = headers["x-ratelimit-remaining"]
                val rateLimitReset = headers["x-ratelimit-reset"]
                Log.d(
                    "RESPONSE",
                    " rate used: $rateLimitUsed |rate limit remaining: $rateLimitRemaining" +
                            "| rate limit reset (seconds) $rateLimitReset"
                )
                Log.d("RESPONSE", "$json")

                val jsonString = json.jsonObject.toString()
                val jsonObject = Json.parseToJsonElement(jsonString)
                val dataObject = jsonObject?.jsonObject?.get("data")
                val childrenArray = dataObject?.jsonObject?.get("children")?.jsonArray

                if (childrenArray != null) {
                    for (childJsonObj in childrenArray) {
                        if (childJsonObj is JsonObject) {
                            val childDataObj = childJsonObj["data"]?.jsonObject
                            val title = childDataObj?.get("title")?.jsonPrimitive?.content
                            val url = childDataObj?.get("url")?.jsonPrimitive?.content

                            Log.d("Parsing Json", "title: $title  | imageURL $url")
//                            TODO("Filter text only urls")
//                            TODO("Save valid text, url pairs for use in activity views")
                        }
                    }
                }
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