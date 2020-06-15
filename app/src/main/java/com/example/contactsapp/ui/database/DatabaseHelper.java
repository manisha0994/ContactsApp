package com.example.contactsapp.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String databasename = "Student";
    Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, databasename, null, 1);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table contactlist (name Text,phone Text,email Text,image Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists contactlist");
        onCreate(sqLiteDatabase);
    }

//// insert data into database
   public boolean insert(String name, String phone, String email, Bitmap imagebitmap){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select phone from contactlist where phone = "+phone,null);
        if(cursor != null && cursor.moveToFirst()){
            return false;
        }
        else{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imagebitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
            byte [] image = byteArrayOutputStream.toByteArray();
            String imagestring = Base64.encodeToString(image,Base64.DEFAULT);

            ContentValues contentValues = new ContentValues();
            contentValues.put("name",name);
            contentValues.put("phone", phone);
            contentValues.put("email",email);
            contentValues.put("image",imagestring);
            long result = sqLiteDatabase.insert("contactlist",null,contentValues);
            //cursor.moveToFirst();
            if(result == -1){
                return false;
            }
            else{
                Toast.makeText(context,"Contact Saved", Toast.LENGTH_LONG).show();
                return true;
            }
        }

    }

    // for delete data from database
    public void delete(String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from contactlist where phone = "+phone);

    }

    public Cursor Getalldata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from contactlist",null);
        return  cursor;

    }
}
