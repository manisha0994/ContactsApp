package com.example.contactsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");

        TextView txtview= findViewById(R.id.tv);
        TextView  pname = findViewById(R.id.p_name);
        CircleImageView ppcimageview = findViewById(R.id.pp_cimageview);

        LocationSDK lsdk = new LocationSDK(phone,findViewById(R.id.container));

        lsdk.getLocation(phone,txtview);
        pname.setText(name);
        byte[] bytes = Base64.decode(image,Base64.DEFAULT);
        ppcimageview.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));

        //Snackbar snackbar = Snackbar.make(findViewById());


    }
}
