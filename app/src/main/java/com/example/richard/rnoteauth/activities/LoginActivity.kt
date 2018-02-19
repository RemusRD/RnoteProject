package com.example.richard.rnoteauth.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.richard.rnoteauth.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.io.FileInputStream
import java.util.*

class LoginActivity : AppCompatActivity() {

    val fireBaseAuth = FirebaseAuth.getInstance()
    val RC_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val currentUser = fireBaseAuth.currentUser
        if (currentUser == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.mipmap.ic_launcher_foreground)
                            .setTheme(R.style.LoginTheme)
                            .setAvailableProviders(
                                    Arrays.asList(AuthUI.IdpConfig.EmailBuilder().build(),
                                            AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN)

        } else {
            startActivity(Intent(this, LastLoginActivity::class.java))
        }

    }
    public fun holaquetal(v:View){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                startActivity(Intent(this, NoteActivity::class.java))
                finish()
                return
            } else {
                if (response == null) {
                    showMessage(root_log_in, "Autenticación cancelada")
                    finish()
                    return
                }
                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    showMessage(root_log_in, "Sin conexión a internet")
                    return
                }
                if(response.errorCode == ErrorCodes.UNKNOWN_ERROR){
                    showMessage(root_log_in, "Error desconocido")
                    return
                }
            }

        }
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show()
    }
}
