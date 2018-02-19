package com.example.richard.rnoteauth.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import co.zsmb.materialdrawerkt.draweritems.sectionHeader
import com.example.richard.rnoteauth.BuildConfig
import com.example.richard.rnoteauth.R
import com.example.richard.rnoteauth.fragments.DrawerFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_fragment_dark_toolbar.*
import org.jetbrains.anko.toast
import org.sufficientlysecure.donations.DonationsFragment
import kotlin.reflect.KClass

class NoteActivity : AppCompatActivity() {
    lateinit var result:Drawer

    companion object {
        private const val PAYPAL_USER = "richarffx@gmail.com"
        private const val PAYPAL_CURRENCY_CODE = "EUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dark_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("RNote")
        val lastLoginIntent = Intent(this, LoginActivity::class.java)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DrawerFragment.newInstance(
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
                    toast("Aún por implementar")
                    false
                }
                ///A implementar donaciones
/*                onClick { _ ->
                    supportFragmentManager.beginTransaction().replace(
                            R.id.fragment_container,
                            DonationsFragment.newInstance(BuildConfig.DEBUG, false, null, null,
                                    null, true, PAYPAL_USER, PAYPAL_CURRENCY_CODE, getString(R.string.donation_paypal_item),
                                    false, null, null, false, null)
                    )

                    false
                }*/
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
