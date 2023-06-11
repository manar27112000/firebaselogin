package com.example.firebasewithgoogle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.AbortVoiceRequest
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.viewmodel.RequestCodes
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.IdToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GithubAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    companion object{
        private const val RC_SIGN_IN =9001
    }
    private lateinit var auth: FirebaseAuth
    var btn_google_sign_in = findViewById<Button>(R.id.btn_google_sign_in);

    private lateinit var googleSignInClient:GoogleSignInClient

    override fun onStart() {
        super.onStart()
        val currentUser =auth.currentUser
        if(currentUser!=null){
            val  intent = Intent(this,home::class.java)
            startActivity(intent)
        }
        updateUI(currentUser)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=Firebase.auth

        //configure google sign in
        var gso =GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        btn_google_sign_in.setOnClickListener{
            signIn()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential =GithubAuthProvider.getCredential(idToken)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                val user =auth.currentUser
                updateUI(user)
            }else{
                Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()

                updateUI(null)
            }

        }

    }

    private fun signIn(){
        val signIntent =googleSignInClient.signInIntent
        startActivityForResult(signIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode:Int, resultCode: Int, data :Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== RC_SIGN_IN){
            val task =GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account =task.getResult(ApiException::class.java)!!
                Toast.makeText(this,"firebase auth with google"+account.id,Toast.LENGTH_SHORT).show()
                  firebaseAuthWithGoogle(account.idToken!!)
            }catch (e:ApiException){
                Toast.makeText(this,"firebase auth with google failed",Toast.LENGTH_SHORT).show()

            }
        }

    }

private fun updateUI(user:FirebaseUser?){}

}