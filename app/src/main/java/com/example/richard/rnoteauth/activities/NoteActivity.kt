package com.example.richard.rnoteauth.activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import co.zsmb.materialdrawerkt.draweritems.sectionHeader
import com.example.richard.rnoteauth.R
import com.example.richard.rnoteauth.fragments.noteRecyclerFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_dark_toolbar.*
import org.jetbrains.anko.toast

class NoteActivity : AppCompatActivity() {
    lateinit var result:Drawer

    companion object {
        private const val PAYPAL_LINK = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=KCDN4LEJWQ2QJ&lc=ES&item_name=Richard%20%3a%29&item_number=ELTESLADERICHARD&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("RNote")
        val lastLoginIntent = Intent(this, LoginActivity::class.java)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, noteRecyclerFragment.newInstance(
                        "DEMO"))
                .commit()


        result = drawer {
            displayBelowStatusBar = true
            savedInstance = savedInstanceState
            accountHeader {
                compactStyle = true
                translucentStatusBar = true
                // background = Color.parseColor("#FDFDFD")
                textColor = Color.BLACK.toLong()
                background  = R.color.primaryColor

                profile {
                    name = FirebaseAuth.getInstance().currentUser!!.displayName!!
                    email = FirebaseAuth.getInstance().currentUser!!.email!!
                }

            }
            actionBarDrawerToggleEnabled = true
            primaryItem("Casita") {
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }
            sectionHeader("Header")
            secondaryItem("Opciones") {
                onClick { _ ->
                    toast("Aún por implementar")
                    false }

            }
            secondaryItem("Ayúdame") {
                onClick { _ ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PAYPAL_LINK)))
                    false
                }
            }
            secondaryItem("Open source") {
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }
            secondaryItem("Contacto") {
                onClick { _ ->
                    toast("Aún por implementar")
                    false

                }
            }

            divider {  }
            primaryItem("Cerrar sesión") {
                onClick { _ ->
                    FirebaseAuth.getInstance().signOut()
                    startActivity(lastLoginIntent)
                    finish()
                    false
                }
            }
        }
        result.drawerLayout.fitsSystemWindows = true

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home)
            result.openDrawer()
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        ///IMPLEMENTAR BÚSQUEDA DE NOTAS
        //val searchItem =  menu.findItem(R.id.searchView)
        //val searchView = searchItem.actionView as SearchView
        //searchView.setIconifiedByDefault(true)
        //searchView.isSubmitButtonEnabled=false
        //searchView.queryHint = getString(R.string.search_menu_title)
        //searchView.setOnQueryTextListener(onQueryTextListener)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        result.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LastLoginActivity::class.java))
        finish()
    }

}
