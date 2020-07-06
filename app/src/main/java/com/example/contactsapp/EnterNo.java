package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.contactsapp.ui.activity.MainActivity;

public class EnterNo extends AppCompatActivity {

    EditText editText;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_no);
        editText = findViewById(R.id.enter_contact);
        save = findViewById(R.id.save_no);

        SharedPreferences sharedPreferences;
        sharedPreferences =EnterNo.this.getSharedPreferences("shared",MODE_PRIVATE);

        if(sharedPreferences.getString("My_phone",null)!=null)
        {
            startActivity(new Intent(EnterNo.this,MainActivity.class));
            finish();
        }
            save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences;
                sharedPreferences =EnterNo.this.getSharedPreferences("shared",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("My_phone",editText.getText().toString());
                editor.commit();
                startActivity(new Intent(EnterNo.this, MainActivity.class));
                finish();
            }
        });
    }

}
