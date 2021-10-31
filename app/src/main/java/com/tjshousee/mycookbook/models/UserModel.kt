package com.tjshousee.mycookbook.models


data class Users(
    val users: ArrayList<UserModelClass>
)

    data class UserModelClass(
        val id: Int = 0,
        val name: String = "",
        val username: String = "",
        val password: String = "",
        val email: String = "",
        val gender: String = "",
        val weight: Int = 0,
        val height: Int = 0,
        val phone: String = "",
        val office: String = ""
    )

    data class Phone(
        val mobile: String = "",
        val office: String = ""
    )
