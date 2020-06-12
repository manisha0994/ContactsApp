package com.example.contactsapp.ui;

import androidx.annotation.NonNull;

public class Model {
    String name,contno,email;

    public Model(){

    }

    public Model(String name, String contno, String email){
        this.name = name;
        this.contno = contno;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return name +" "+contno+" "+email;
    }
}
