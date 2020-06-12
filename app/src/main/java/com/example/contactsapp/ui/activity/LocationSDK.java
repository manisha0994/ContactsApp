package com.example.contactsapp.ui.activity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.contactsapp.ui.SharedLocationModel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LocationSDK implements GoogleApiClient.ConnectionCallbacks {

    FusedLocationProviderClient locationProviderClientocation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String phone;
    LocationSDK(String phone){
       this.phone = phone;
    }

    public void startLocation(final Context context){
        Toast.makeText(context, "Sharing....", Toast.LENGTH_SHORT).show();
        locationProviderClientocation = LocationServices.getFusedLocationProviderClient(context);
        locationProviderClientocation.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    if (task.getResult() != null) {
                        String abc = String.valueOf(task.getResult().getLatitude()+task.getResult().getLongitude());

                        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);

                        try {
                            List<Address> addresses = geocoder.getFromLocation(task.getResult().getLatitude(), task.getResult().getLongitude(), 1);
                            if (addresses != null && addresses.size() > 0)
                            {

                                  /* Log.d("Location Found",addresses.get(0).getAddressLine(0));
                                    tv.setText(addresses.get(0).getAddressLine(0));*/



                                // Create a new user with a first and last name
                                Map<String, Object> user = new HashMap<>();
                                user.put("loc", addresses.get(0).getAddressLine(0));
                                user.put("latlng", task.getResult().getLatitude()+","+task.getResult().getLongitude());


// Add a new document with a generated ID
                                db.collection("users")
                                        .document(phone)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("TAG", "onSuccess: Save");
                                                Toast.makeText(context, "Shared Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            else
                                {
                                assert addresses != null;

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {

                        //Log.d("Location Found",task.getResult().toString());
                               // tv.setText("Not Found");
                    }
                }
            }
        });
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    public void getLocation(String phone, final TextView tv){

        db.collection("users")
                .document(phone)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                SharedLocationModel sharedLocationModel = documentSnapshot.toObject(SharedLocationModel.class);
                tv.setText(sharedLocationModel.loc);

                Log.d("TAG", "onSuccess: Got");

            }
        });


    }

}
