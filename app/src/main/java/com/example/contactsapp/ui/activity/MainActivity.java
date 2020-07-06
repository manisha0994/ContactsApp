package com.example.contactsapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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



        CheckPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_CONTACTS,Manifest.permission.CALL_PHONE},101);

        //        CheckPermissions(Manifest.permission.WRITE_CONTACTS,111);
//        CheckPermissions(Manifest.permission.CALL_PHONE,121);


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
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("shared",MODE_PRIVATE);
                LocationSDK locationSDK = new LocationSDK(sharedPreferences.getString("My_phone",null),findViewById(R.id.maincontainer));
                locationSDK.startLocation(MainActivity.this);
            }
        });


    }
     void CheckPermissions(String[] permission, int requeat_code){
        //if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,permission,requeat_code);

        }
        //else{
//            Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_LONG);
//            CheckPermissions(Manifest.permission.ACCESS_FINE_LOCATION,101);
//            CheckPermissions(Manifest.permission.WRITE_CONTACTS,111);
//            CheckPermissions(Manifest.permission.CALL_PHONE,121);
//        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}

