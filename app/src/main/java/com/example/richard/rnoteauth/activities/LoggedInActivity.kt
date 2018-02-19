package com.example.richard.rnoteauth.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.richard.rnoteauth.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
///QUITAR ESTA ACTIVIDAD
class LoggedInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
    }

    private fun signOut(){
        AuthUI.getInstance().signOut(this).addOnCompleteListener(OnCompleteListener<Void>() {
            fun onComplete(task:Task<Void>){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
    }
}
