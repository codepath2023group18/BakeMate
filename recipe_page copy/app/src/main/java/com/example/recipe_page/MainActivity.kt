package com.example.recipe_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var recipeList: MutableList<String>
    private lateinit var rvRecipes: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvRecipes = findViewById(R.id.repcipe_list)
        recipeList = mutableListOf()
        getRecipeImageURL()
    }
    private fun getRecipeImageURL(id: String? = "") {
        val client = AsyncHttpClient()

        client["https://spoonacular.com/food-api", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val petImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until petImageArray.length()) {
                    recipeList.add(petImageArray.getString(i))
                }

                val adapter = RecipeAdapter(recipeList)
                rvRecipes.adapter = adapter
                rvRecipes.layoutManager = LinearLayoutManager(this@MainActivity)
                rvRecipes.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }
    override fun onFailure(
        statusCode: Int,
        headers: Headers?,
        errorResponse: String,
        throwable: Throwable?
    ) {
        Log.d(" error", errorResponse)
    }
   }]

}

}