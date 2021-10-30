package com.tjshousee.mycookbook.models


data class Users(
    val users: ArrayList<UserModelClass>
)

    data class UserModelClass(
        val id: Int,
        val name: String,
        val email: String,
        val gender: String,
        val weight: Double,
        val height: Int,
        val phone: String,
        val office: String
    )

    data class Phone(
        val mobile: String,
        val office: String
    )
