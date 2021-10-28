package com.tjshousee.mycookbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tjshousee.mycookbook.R

import com.tjshousee.mycookbook.databinding.CardRecipeBinding
import com.tjshousee.mycookbook.databinding.ActivityRecipeListBinding
import com.tjshousee.mycookbook.main.MainApp
import com.tjshousee.mycookbook.models.RecipeModel
class RecipeListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRecipeListBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)


        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RecipeAdapter(app.recipes)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, CookbookActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class RecipeAdapter constructor(private var recipies: List<RecipeModel>) :
    RecyclerView.Adapter<RecipeAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecipeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val recipe = recipies[holder.adapterPosition]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipies.size

    class MainHolder(private val binding: CardRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeModel) {
            binding.recipeTitle.text = recipe.title
            binding.recipeDescription.text = recipe.description
            binding.recipeIngredients.text = recipe.ingredients


        }
    }
}