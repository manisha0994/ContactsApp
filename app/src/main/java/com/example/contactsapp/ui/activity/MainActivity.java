package com.example.contactsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.contactsapp.R;
import com.example.contactsapp.ui.fragment.ContactsFragment;
import com.example.contactsapp.ui.fragment.PhoneFragment;

public class MainActivity extends AppCompatActivity {
    PhoneFragment phoneFragment;
    ContactsFragment contactsFragment;
    FragmentManager fragmentManager;
    Button phone,contacts;
    ImageButton addcount;
    ImageButton sharelocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phone);
        contacts = findViewById(R.id.contacts);
        addcount = findViewById(R.id.addcount);
        sharelocation = findViewById(R.id.sharedlocation);


        fragmentManager = getSupportFragmentManager();
        phoneFragment = new PhoneFragment();
        contactsFragment = new ContactsFragment();
        fragmentManager.beginTransaction().replace(R.id.flayout, phoneFragment).commit();

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.flayout, phoneFragment).commit();

            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction().replace(R.id.flayout, contactsFragment).commit();

            }
        });
        addcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(i);
            }
        });
        sharelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationSDK locationSDK = new LocationSDK("9234513931",findViewById(R.id.maincontainer));
                locationSDK.startLocation(MainActivity.this);
            }
        });

    }
}
