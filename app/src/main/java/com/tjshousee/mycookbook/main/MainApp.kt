package com.tjshousee.mycookbook.main

import android.app.Application
import com.tjshousee.mycookbook.models.RecipeModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val recipes = ArrayList<RecipeModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Cookbook started")

      //  recipes.add(RecipeModel("One", "About one...", "ham & cheese"))
       // recipes.add(RecipeModel("Two", "About one...", "ham & cheese"))
       // recipes.add(RecipeModel("Three", "About one...", "ham & cheese"))

    }
}