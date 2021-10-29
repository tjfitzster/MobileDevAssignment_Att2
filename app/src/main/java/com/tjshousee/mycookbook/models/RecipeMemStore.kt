package com.tjshousee.mycookbook.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}


class RecipeMemStore : RecipeStore {

    val recipes = ArrayList<RecipeModel>()

    override fun findAll(): List<RecipeModel> {
        return recipes
    }

    override fun create(recipe: RecipeModel) {
        recipe.id = getId()
        recipes.add(recipe)
        logAll()
    }

    override fun update(recipe: RecipeModel) {
        var foundRecipe: RecipeModel? = recipes.find { p -> p.id == recipe.id }
        if (foundRecipe != null) {
            foundRecipe.id = recipe.id
            foundRecipe.title = recipe.title
            foundRecipe.description = recipe.description
            foundRecipe.ingredients = recipe.ingredients
            foundRecipe.favorite = recipe.favorite
            foundRecipe.image = recipe.image
            logAll()
        }
    }

    private fun logAll() {
        recipes.forEach{ i("${it}") }
    }
}