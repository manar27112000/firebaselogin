package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
   var signin_main = findViewById<Button>(R.id.signin_main);
   var signup_main = findViewById<Button>(R.id.signup_main);

   // var signup_main = findViewById<Button>(R.id.signup_main);
   // var signup_main = findViewById<Button>(R.id.signup_main);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        auth = Firebase.auth
        signin_main.setOnClickListener {
           val intent = Intent(this,login::class.java)
            startActivity(intent)
        }
        signup_main.setOnClickListener {
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
                if(currentUser !=null){
                    val intent=Intent(this,home::class.java)
                     startActivity(intent)
                }
    }
}
