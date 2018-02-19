package com.example.richard.rnoteauth.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.richard.rnoteauth.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_last_login.*
import org.jetbrains.anko.alert

class LastLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_login)
        val logOutIntent = Intent(this, LoginActivity::class.java)




        wellcomeTextView.text = "Buenos días, " + FirebaseAuth.getInstance().currentUser!!.displayName
        logOutTextView.text = "¿No eres " + FirebaseAuth.getInstance().currentUser!!.displayName + " ?"
        logOutTextView.setOnClickListener {
            alert("¿Estás seguro?") {
                title = "Cerrar sesión"
                positiveButton("Sí") {
                    title = "Sí"
                    FirebaseAuth.getInstance().signOut()
                    startActivity(logOutIntent)
                    finish()
                }
                negativeButton("Cancelar") {}
            }.show()

        }
        entrarBoton.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        super.onBackPressed()
    }
}
