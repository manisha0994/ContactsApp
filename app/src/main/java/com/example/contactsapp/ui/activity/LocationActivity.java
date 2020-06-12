package com.example.contactsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.contactsapp.R;
import com.google.android.material.snackbar.Snackbar;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        String phone = getIntent().getStringExtra("phone");

        TextView txtview= findViewById(R.id.tv);

        LocationSDK lsdk = new LocationSDK(phone,findViewById(R.id.container));

        lsdk.getLocation(phone,txtview);
        //Snackbar snackbar = Snackbar.make(findViewById());


    }
}
