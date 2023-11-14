package com.example.bakemate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeList: MutableList<String>
    private lateinit var rvRecipes: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipie)
        rvRecipes = findViewById(R.id.recipe_list)
        recipeList = mutableListOf()
//        getRecipeImageURL()

        // Link buttons
        val profileButton = findViewById<Button>(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val homeButton = findViewById<Button>(R.id.home_button)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val recipesButton = findViewById<Button>(R.id.recipes_button)
        recipesButton.setOnClickListener {
            val intent = Intent (this, RecipeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getRecipeImageURL(id: String? = "") {
        TODO("Reimplement Spoonacular API")
        val client = AsyncHttpClient()

        client["https://spoonacular.com/food-api", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val petImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until petImageArray.length()) {
                    recipeList.add(petImageArray.getString(i))
                }

                val adapter = RecipeAdapter(recipeList)
                rvRecipes.adapter = adapter
                rvRecipes.layoutManager = LinearLayoutManager(this@RecipeActivity)
                rvRecipes.addItemDecoration(DividerItemDecoration(this@RecipeActivity, LinearLayoutManager.VERTICAL))
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