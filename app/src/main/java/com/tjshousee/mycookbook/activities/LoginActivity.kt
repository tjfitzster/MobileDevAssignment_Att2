package com.tjshousee.mycookbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.tjshousee.mycookbook.R

import com.tjshousee.mycookbook.models.UserModelClass
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import com.tjshousee.mycookbook.databinding.ActivityLoginBinding
import java.nio.charset.Charset

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        val usersList: ArrayList<UserModelClass> = ArrayList()
        var user = UserModelClass()


        super.onCreate(savedInstanceState)



        try {
            // As we have JSON object, so we are getting the object
         //Here we are calling a Method which is returning the JSON object
         val obj = JSONObject(getJSONFromAssets()!!)
         // fetch JSONArray named users by using getJSONArray
         val usersArray = obj.getJSONArray("users")
         // Get the users data using for loop i.e. id, name, email and so on

             for (i in 0 until usersArray.length()) {
                 // Create a JSONObject for fetching single User's Data
                 val user = usersArray.getJSONObject(i)
                 // Fetch id store it in variable
                 val id = user.getInt("id")
                 val name = user.getString("name")
                 val email = user.getString("email")
                 val username = user.getString("username")
                 val password = user.getString("password")
                 val gender = user.getString("gender")
                 val weight = user.getInt("weight")
                 val height = user.getInt("height")

                 // create a object for getting phone numbers data from JSONObject
                 val phone = user.getJSONObject("phone")
                 // fetch mobile number
                 val mobile = phone.getString("mobile")
                 // fetch office number
                 val office = phone.getString("office")

                 // Now add all the variables to the data model class and the data model class to the array list.
                 val userDetails =
                     UserModelClass(id, name, email, username, password, gender, weight, height, mobile, office)

                 // add the details in the list
                 usersList.add(userDetails)
             }

            setContentView(R.layout.activity_login)

            binding.loginbutton.setOnClickListener() {

                var username : String = binding.username.text.toString()
                var pwd : String = binding.password.text.toString()


                if (username.isEmpty()) {
                    Snackbar.make(it,R.string.Username_enter, Snackbar.LENGTH_LONG)
                        .show()
                } else {

                    for (i in usersList.indices) {
                        if (usersList[i].username.equals(username))
                        {
                            val intent = Intent(this, CookbookActivity::class.java)
                            startActivity(intent)

                            // email address correct

                            //if (usersList[i].password.equals(pwdpassword))
                            // {
                            // PASSWROD correct
                            //     Toast.makeText(this, "PASSWORD CORRECT", Toast.LENGTH_SHORT).show()
                            //   }
                            // else{
                            //     Toast.makeText(this, "PASSWORD INCORRECT", Toast.LENGTH_SHORT).show()
                            //    Toast.makeText(this, "this.$users[i].password", Toast.LENGTH_SHORT).show()
                            //  }
                        }

                    }



                }
                setResult(RESULT_OK)
                finish()

            }



        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }





    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("Users.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}