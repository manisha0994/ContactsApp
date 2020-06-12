package com.example.contactsapp.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapp.R;
import com.example.contactsapp.ui.Model;
import com.example.contactsapp.ui.database.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {
    ArrayList<Model> arrayList = new ArrayList<>();
    ImageView imageView;
    ImageButton cmr;
    EditText nametext,contno,emailedit;
    Button save;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        imageView = findViewById(R.id.imgview);
        cmr = findViewById(R.id.cmr);
        nametext = findViewById(R.id.nametext);
        contno = findViewById(R.id.contno);
        emailedit = findViewById(R.id.emailedit);
        save = findViewById(R.id.save);
        databaseHelper = new DatabaseHelper(this);

        cmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(databaseHelper.insert(nametext.getText().toString(),contno.getText().toString(),emailedit.getText().toString())){
                    Log.d("Database", "Inserted ");
                }
                else{
                    Log.d("Database","Not Inserted");
                }
                getdata();
               // saveToContact();
            }
        });
    }
    void saveToContact(){

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        Log.i("Line38", "Here");
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, AccountManager.KEY_ACCOUNT_TYPE)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, AccountManager.KEY_ACCOUNT_NAME)
                .build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "XYZ")
                .withValue(ContactsContract.CommonDataKinds.StructuredName.IN_VISIBLE_GROUP,true)
                .build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,"23232343434")
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, "4343")
                .build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, "text@gmail.com")
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, "2")
                .build());

        //Log.i("Line43", Data.CONTENT_URI.toString()+" - "+rawContactInsertIndex);

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("TAG", "saveToContact: Remote ");
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("TAG", "saveToContact: Operations");
        }
    }
    public void getdata(){
        DatabaseHelper databaseHelper = new DatabaseHelper(AddContactActivity.this);
        Cursor cursor = databaseHelper.Getalldata();

        Model model = new Model();
        arrayList.clear();
        while(cursor.moveToNext()){

            //model = new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            arrayList.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
        }
       // Log.d("TAG", model.toString());
        Log.d("TAG", arrayList.size()+" ");
    }
    public void openCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,123);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
