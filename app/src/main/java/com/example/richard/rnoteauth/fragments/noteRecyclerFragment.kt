package com.example.richard.rnoteauth.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.richard.rnoteauth.R
import com.example.richard.rnoteauth.adapters.NoteViewHolder
import com.example.richard.rnoteauth.data.textNote
import com.example.richard.rnoteauth.helpers.AbstractSwipeCallback
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.recycler_fragment_layout.*
import org.jetbrains.anko.support.v4.toast


class noteRecyclerFragment : Fragment() {


/*    private val speedDialSizeOptions = arrayOf(
            Pair("None", 0),
            Pair("1 None", 1),
            Pair("2 None", 2),
            Pair("3 None", 3),
            Pair("4 None", 4)
    )
    private var speedDialSize = 0*/


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.recycler_fragment_layout, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        drawer_recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        fab.setButtonIconResource(R.drawable.ic_add)


        ///A IMPLEMENTAR BOTÓN FAB CON OPCIONES
        /*val speedDialMenuAdapter = object : SpeedDialMenuAdapter() {
            override fun getCount(): Int {
                return speedDialSizeOptions[speedDialSize].second
            }

            override fun getMenuItem(context: Context, position: Int): SpeedDialMenuItem = when (position) {
                0 -> SpeedDialMenuItem(context, R.drawable.common_google_signin_btn_icon_dark, "item one")
                1 -> SpeedDialMenuItem(context, R.drawable.common_google_signin_btn_icon_dark, "item two")
                2 -> SpeedDialMenuItem(context, R.drawable.common_google_signin_btn_icon_dark, "item three")
                3 -> SpeedDialMenuItem(context, R.drawable.common_google_signin_btn_icon_dark, "item four")
                else -> throw IllegalArgumentException("No menu item: $position")
            }

            override fun fabRotationDegrees(): Float = if(buttonIcon == 0) 135F else 0F;
        }*/

        //fab.speedDialMenuAdapter = speedDialMenuAdapter;
        fab.contentCoverEnabled = true

        var query = FirebaseDatabase.getInstance().reference.child(FirebaseAuth.getInstance().currentUser!!.uid)
        val options = FirebaseRecyclerOptions.Builder<textNote>().setQuery(query, textNote::class.java).build()

        val myAdapter = object : FirebaseRecyclerAdapter<textNote, NoteViewHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteViewHolder {
                return NoteViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.note_row, parent, false))
            }

            override fun onBindViewHolder(holder: NoteViewHolder, position: Int, model: textNote) {

                with(holder) {
                    setTextView_title(model.title)
                    setTextView_text(model.mainText)
                    setTextView_lastMod(model.lastMod)
                    setImageView_icon(builder.build(model.title[0].toString().toUpperCase(), ColorGenerator.MATERIAL.getColor(model.title)))
                }
            }


        }
        myAdapter.startListening()

        with(drawer_recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = myAdapter
            addItemDecoration(DividerItemDecoration(drawer_recyclerView.context, DividerItemDecoration.VERTICAL))
        }

        val rightSwipeHandler = object : AbstractSwipeCallback(context = context!!, swipeDirection = ItemTouchHelper.RIGHT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                //myAdapter.getRef(viewHolder!!.adapterPosition).removeValue()
                toast("Nota Archivada")

            }

        }
        val leftSwipeHandler = object : AbstractSwipeCallback(context = context!!, swipeDirection = ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                myAdapter.getRef(viewHolder!!.adapterPosition).removeValue()
                toast("Nota Borrada")

            }

        }
        ItemTouchHelper(rightSwipeHandler).attachToRecyclerView(drawer_recyclerView)
        ItemTouchHelper(leftSwipeHandler).attachToRecyclerView(drawer_recyclerView)
    }

    companion object {
        ///EDITAR PARÁMETRO DEL MÉTODO
        val builder = TextDrawable.builder().beginConfig().withBorder(4).endConfig().round()

        fun newInstance(title: String) =
                noteRecyclerFragment().apply {
                }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return true
    }


}
