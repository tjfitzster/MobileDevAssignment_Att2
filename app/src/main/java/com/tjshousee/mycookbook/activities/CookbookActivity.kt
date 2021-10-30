package com.tjshousee.mycookbook.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.tjshousee.mycookbook.R
import timber.log.Timber
import timber.log.Timber.i
import com.tjshousee.mycookbook.databinding.ActivityCookbookBinding
import com.tjshousee.mycookbook.helpers.showImagePicker
import com.tjshousee.mycookbook.main.MainApp
import com.tjshousee.mycookbook.models.RecipeModel
import com.squareup.picasso.Picasso


class CookbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCookbookBinding


    var recipe = RecipeModel()

    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityCookbookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        registerImagePickerCallback()

        i("Cookbook Activity started...")

        if (intent.hasExtra("recipe_edit")) {
            edit = true
            recipe = intent.extras?.getParcelable("recipe_edit")!!
            binding.recipeTitle.setText(recipe.title)
            binding.recipeDescription.setText(recipe.description)
            binding.recipeIngredients.setText(recipe.ingredients)
            binding.btnAddrecipe.setText(R.string.save_recipe)
            Picasso.get()
                .load(recipe.image)
                .into(binding.recipeImage)
        }

        binding.btnAddrecipe.setOnClickListener() {
            recipe.title = binding.recipeTitle.text.toString()
            recipe.description = binding.recipeDescription.text.toString()
            recipe.ingredients = binding.recipeIngredients.text.toString()

            if (recipe.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_recipe_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.recipes.update(recipe.copy())

                } else {
                    app.recipes.create(recipe.copy())

                }

            }
            setResult(RESULT_OK)
            finish()

        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.searchGoogle.setOnClickListener {

            if (binding.recipeTitle.text.toString().isEmpty()) {
                Snackbar.make(it, " Enter A search Term", Snackbar.LENGTH_LONG)
                    .show()
            }
            else
            {
                val site = Intent(Intent.ACTION_VIEW)
                var searchTerm: String? = binding.recipeTitle.text.toString()
                searchTerm += " Recipe"
                site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                startActivity(site)
            }
        }
        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            recipe.image = result.data!!.data!!
                            Picasso.get()
                                .load(recipe.image)
                                .into(binding.recipeImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}