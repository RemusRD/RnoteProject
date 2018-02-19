package com.example.richard.rnoteauth.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.richard.rnoteauth.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    View mView;
    private TextView textView_title;
    private TextView textView_text;
    private TextView textView_lastMod;

    public NoteViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        textView_title = (TextView) itemView.findViewById(R.id.textview_note_title);
        textView_text = (TextView) itemView.findViewById(R.id.textView_note_main_text);
        textView_lastMod = (TextView) itemView.findViewById(R.id.textView_ultima_mod);
    }


    public void setTextView_title(String title) {
        this.textView_title.setText(title);
    }

    public void setTextView_text(String text) {
        this.textView_text.setText(text);
    }

    public void setTextView_lastMod(String lastMod) {
        this.textView_lastMod.setText(lastMod);
    }
}
