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

    private lateinit var feedImageList: MutableList<String>
    private lateinit var titleList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO("Create Feed Recycler")
        //feedRecyclerView = findViewById(R.id.feed_images_list)
        feedImageList = mutableListOf()
        titleList = mutableListOf()
        getRedditData()
    }

    private fun getRedditData() {
        val client = AsyncHttpClient()

        client["https://api.reddit.com/r/baking/new?limit=50", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                // Access a print JSON response and relevant Headers to log
                Log.d("OBJECT", json.jsonObject.toString())
                val rateLimitUsed = headers["x-ratelimit-used"]
                val rateLimitRemaining = headers["x-ratelimit-remaining"]
                val rateLimitReset = headers["x-ratelimit-reset"]
                Log.d(
                    "RESPONSE",
                    " rate used: $rateLimitUsed |rate limit remaining: $rateLimitRemaining" +
                            "| rate limit reset (seconds) $rateLimitReset"
                )
                Log.d("RESPONSE", "$json")
                // Parse Json for title and img url
                val jsonString = json.jsonObject.toString()
                val jsonObject = Json.parseToJsonElement(jsonString)
                val dataObject = jsonObject?.jsonObject?.get("data")
                val childrenArray = dataObject?.jsonObject?.get("children")?.jsonArray

                if (childrenArray != null) {
                    for (childJsonObj in childrenArray) {
                        if (childJsonObj is JsonObject) {
                            val childDataObj = childJsonObj["data"]?.jsonObject
                            val imgTitle = childDataObj?.get("title")?.jsonPrimitive?.content
                            var imageUrl = childDataObj?.get("url")?.jsonPrimitive?.content

                            if (imageUrl?.startsWith("https://i.redd.it") == true) {
                                feedImageList.add(imageUrl.toString())
                                titleList.add(imgTitle.toString())
                            } else {
                                continue
                            }

                            Log.d("Parsing Json", "title: $title  | imageURL $imageUrl")

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