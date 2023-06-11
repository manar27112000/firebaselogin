package com.example.firebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    var username_signup = findViewById<EditText>(R.id.username_signup);
    var password_signup = findViewById<EditText>(R.id.password_signup);
    var signup = findViewById<Button>(R.id.signup);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = Firebase.auth
         signup.setOnClickListener{
    auth.createUserWithEmailAndPassword(username_signup.text.toString().trim(), password_signup.text.toString().trim())
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(baseContext, "sign_up Done.",
                    Toast.LENGTH_SHORT).show()
                val intent= Intent(this,home::class.java)
                startActivity(intent)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(baseContext, "sign_up failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
}

    }
}