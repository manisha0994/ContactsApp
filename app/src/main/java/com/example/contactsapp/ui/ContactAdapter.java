package com.example.contactsapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contactsapp.R;
import com.example.contactsapp.ui.activity.LocationActivity;
import com.example.contactsapp.ui.database.DatabaseHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    ArrayList<Model> arrayList = null;
    Context context = null;

    public ContactAdapter(ArrayList<Model> list) {
        arrayList = list;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_contacts, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        context = parent.getContext();
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.icon.setText(arrayList.get(position).name);
        byte[] bytes = Base64.decode(arrayList.get(position).image,Base64.DEFAULT);
        holder.contimage.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
        holder.imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call(arrayList.get(position).contno);
                 functionCall(arrayList.get(position).contno);
            }
        });

        holder.imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email(arrayList.get(position).email);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocationActivity.class);
                intent.putExtra("phone",arrayList.get(position).contno);
                intent.putExtra("name",arrayList.get(position).name);
                intent.putExtra("image",arrayList.get(position).image);
                context.startActivity(intent);
            }
        });

        holder.imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.delete(arrayList.get(position).contno);
                arrayList.remove(arrayList.get(position));
                notifyDataSetChanged();
            }
        });
    }
    void call(String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        context.startActivity(intent);
    }
    void email(String email){
        Intent i = new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"));
        context.startActivity(i);
    }

    void functionCall(String number) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + number));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class  ContactViewHolder extends RecyclerView.ViewHolder {

        TextView icon;
        CircleImageView contimage;
        ImageButton imgbtn1,imgbtn2,imgbtn3;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            imgbtn1 = itemView.findViewById(R.id.imgbtn1);
            imgbtn2 = itemView.findViewById(R.id.imgbtn2);
            imgbtn3 = itemView.findViewById(R.id.imgbtn3);
            contimage = itemView.findViewById(R.id.contimage);
        }
    }

}
