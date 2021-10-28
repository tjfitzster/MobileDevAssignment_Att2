package com.tjshousee.mycookbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.i
import com.tjshousee.mycookbook.databinding.ActivityCookbookBinding
import com.tjshousee.mycookbook.main.MainApp
import com.tjshousee.mycookbook.models.RecipeModel


class CookbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCookbookBinding
    var recipe = RecipeModel()

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCookbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        i("Cookbook Activity started...")

        binding.btnAddrecipe.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.recipeDescription.text.toString()
            recipe.ingredients = binding.recipeIngredients.text.toString()

            if (recipe.title.isNotEmpty()) {
                app.recipes.add(recipe.copy())
                i("add Button Pressed: $recipe.title")

                for (i in app.recipes.indices) {
                    i("Recipe[$i]:${app.recipes[i]}")
                }
            }
            else {
                Snackbar.make(it,"Please Enter a Recipe", Snackbar.LENGTH_LONG).show()
            }

        }

    }
}