package com.tjshousee.mycookbook.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class RecipeModel (var id: Long = 0,
                    var title: String ="",
                   var description: String ="",
                   var ingredients: String ="",
                        var favorite: Boolean = false,
                        var image: Uri = Uri.EMPTY) : Parcelable