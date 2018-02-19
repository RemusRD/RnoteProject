package com.example.richard.rnoteauth.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.richard.rnoteauth.R
import com.example.richard.rnoteauth.data.MyItem
import com.example.richard.rnoteauth.helpers.DateHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_text_note.*


class NewTextNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_text_note)
        toolbar.setTitle("Creación de nota")
        setSupportActionBar(toolbar)


        notebooksSpinner.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayOf("Ninguno", "Notas importantes", "Notas no tan importantes"))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_send){
            if(TextUtils.isEmpty(noteNameEditText.text)) {
                noteNameEditText.setError("La nota debe tener un nombre")
            } else {
                FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().currentUser!!.uid).push().
                        setValue(MyItem(noteNameEditText.text.toString(), noteTextEditText.text.toString(),
                                DateHelper.getCurrentDate()))
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed()  {
        super.onBackPressed()
        finish()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_note, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
