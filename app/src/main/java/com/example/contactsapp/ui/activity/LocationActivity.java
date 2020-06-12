package com.example.contactsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.contactsapp.R;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        String phone = getIntent().getStringExtra("phone");
        TextView txtview= findViewById(R.id.tv);

        LocationSDK lsdk = new LocationSDK(phone);

        lsdk.getLocation(phone,txtview);


    }
}
