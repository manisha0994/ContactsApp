package com.example.contactsapp.ui;

import androidx.annotation.NonNull;

public class Model {
    String name,contno,email;
    String image;

    public Model(){

    }

    public Model(String name, String contno, String email,String image){
        this.name = name;
        this.contno = contno;
        this.email = email;
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return name +" "+contno+" "+email;
    }
}
