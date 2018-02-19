package com.example.richard.rnoteauth.data;


import org.parceler.Parcel;

@Parcel
public class MyItem {

    private String title;
    private String mainText;
    private String lastMod;

    //constructor vacío para firebase
    public MyItem(){}
    public MyItem(String title, String mainText, String lastMod){
        this.title = title;
        this.mainText = mainText;
        this.lastMod = lastMod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public void setLastMod(String lastMod){
        this.lastMod = lastMod;
    }
    public String getLastMod(){
        return lastMod;
    }
}