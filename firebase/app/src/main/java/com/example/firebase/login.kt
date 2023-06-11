package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    var username = findViewById<EditText>(R.id.username);
    var password = findViewById<EditText>(R.id.password);
    var login = findViewById<Button>(R.id.login);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = Firebase.auth


        username.setOnClickListener {
            auth.signInWithEmailAndPassword(username.text.toString().trim(),
                password.text.toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "sign_in Done.",
                            Toast.LENGTH_SHORT).show()
                        val intent= Intent(this,home::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "sign_in failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}