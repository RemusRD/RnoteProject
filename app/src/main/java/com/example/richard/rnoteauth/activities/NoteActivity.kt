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
import co.zsmb.materialdrawerkt.builders.footer
import co.zsmb.materialdrawerkt.draweritems.badge
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.expandable.expandableBadgeItem
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import com.example.richard.rnoteauth.R
import com.example.richard.rnoteauth.fragments.noteRecyclerFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_dark_toolbar.*
import org.jetbrains.anko.toast

class NoteActivity : AppCompatActivity() {
    lateinit var result: Drawer

    companion object {
        private const val PAYPAL_LINK = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=KCDN4LEJWQ2QJ&lc=ES&item_name=Richard%20%3a%29&item_number=ELTESLADERICHARD&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tus notas"
        val lastLoginIntent = Intent(this, LoginActivity::class.java)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, noteRecyclerFragment.newInstance(
                        "DEMO"))
                .commit()


        result = drawer {
            onTitleChanged("CURRENT", R.color.primaryDarkColor)
            displayBelowStatusBar = true
            savedInstance = savedInstanceState
            accountHeader {
                compactStyle = true
                translucentStatusBar = true
                textColor = Color.BLACK.toLong()
                background = R.color.primaryColor

                profile {
                    name = FirebaseAuth.getInstance().currentUser!!.displayName!!
                    email = FirebaseAuth.getInstance().currentUser!!.email!!
                    icon = R.drawable.ic_person_black_24dp
                }

            }
            actionBarDrawerToggleEnabled = true
            primaryItem("Todas tus notas") {

                iicon = FontAwesome.Icon.faw_sticky_note
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }

            expandableBadgeItem("Cuadernos") {

                badge("2")
                iicon = FontAwesome.Icon.faw_book
                secondaryItem("Cuaderno 1") {

                    iicon = GoogleMaterial.Icon.gmd_book
                    level = 2
                    onClick { _ ->
                        toast("Aún por implementar")
                        false
                    }
                }
                secondaryItem("Cuaderno 2") {


                    iicon = GoogleMaterial.Icon.gmd_book
                    level = 2
                    onClick { _ ->
                        toast("Aún por implementar")
                        false
                    }
                }
            }
            primaryItem("Modo sin conexión") {

                iicon = FontAwesome.Icon.faw_wifi
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }
            divider {

            }

            secondaryItem("Opciones") {
                iicon = FontAwesome.Icon.faw_cog
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }

            }
            secondaryItem("Open source") {

                iicon = FontAwesome.Icon.faw_github
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }
            secondaryItem("Contacto") {

                iicon = FontAwesome.Icon.faw_bullhorn
                onClick { _ ->
                    toast("Aún por implementar")
                    false

                }
            }

            secondaryItem("Acerca de") {

                iicon = FontAwesome.Icon.faw_users
                onClick { _ ->
                    toast("Aún por implementar")
                    false
                }
            }
            secondaryItem("Ayúdame") {

                iicon = FontAwesome.Icon.faw_paypal
                onClick { _ ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(PAYPAL_LINK)))
                    false
                }
            }

            footer {

                primaryItem("Reportar un problema") {

                    iicon = FontAwesome.Icon.faw_bug
                    onClick { _ ->
                        toast("Aún por implementar")
                        false

                    }
                }
                primaryItem("Perfil") {

                    iicon = FontAwesome.Icon.faw_user
                    onClick { _ ->
                        toast("Aún por implementar")
                        false

                    }
                }
                primaryItem("Cerrar sesión") {

                    iicon = FontAwesome.Icon.faw_sign_out_alt
                    onClick { _ ->
                        FirebaseAuth.getInstance().signOut()
                        startActivity(lastLoginIntent)
                        finish()
                        false
                    }
                }
            }
        }
        result.drawerLayout.fitsSystemWindows = true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            result.openDrawer()
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        with(supportActionBar!!) {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        ///IMPLEMENTAR BÚSQUEDADE NOTAS
        //val searchItem =  menu.findItem(R.id.searchView)
        //val searchView = searchItem.actionView as SearchView
        //searchView.setIconifiedByDefault(true)
        //searchView.isSubmitButtonEnabled=false
        //searchView.queryHint = getString(R.string.search_menu_title)

        //searchView.setOnQueryTextListener()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        result.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LastLoginActivity::class.java))
        toast("Pulsa otra vez atrás para cerrar sesión")
        finish()
    }

}
