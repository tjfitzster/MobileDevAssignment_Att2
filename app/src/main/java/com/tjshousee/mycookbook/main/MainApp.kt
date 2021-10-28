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
        i("Placemark started")
    }
}