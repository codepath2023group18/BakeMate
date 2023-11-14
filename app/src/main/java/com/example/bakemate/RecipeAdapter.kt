package com.example.bakemate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter (private val recipeList: MutableList<String>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userPic: ImageView
        val recipeName: TextView
        val informationText: TextView
//        var toast: Toast? = null

        init {
            userPic = view.findViewById(R.id.user_pic)
            recipeName = view.findViewById(R.id.recipename)
            informationText = view.findViewById(R.id.information)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main, viewGroup, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(recipeList[position])
//           placeholder(R.drawable.goodMeal)
            .centerCrop()
            .into(holder.userPic)


        holder.recipeName.text = recipeList[position]
        holder.informationText.text = if (recipeList[position] != "") {
            recipeList[position]
        } else { "Text don't show" }

        holder.userPic.setOnClickListener {
//            if (holder.toast != null) { holder.toast?.cancel() }
            Toast.makeText(holder.itemView.context, "Recipe at position $position clicked", Toast.LENGTH_SHORT)
//            viewHolder.toast?.show()
        }
    }

    override fun getItemCount() = recipeList.size

}