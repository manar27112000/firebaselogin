package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class home : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth =Firebase.auth
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       var id =item?.itemId
        if(id==R.id.itemLogout){
            Firebase.auth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}