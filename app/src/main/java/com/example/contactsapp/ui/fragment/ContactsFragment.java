package com.example.contactsapp.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contactsapp.R;
import com.example.contactsapp.ui.ContactAdapter;
import com.example.contactsapp.ui.Model;
import com.example.contactsapp.ui.activity.AddContactActivity;
import com.example.contactsapp.ui.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    ArrayList <Model> arrayList = new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = view.findViewById(R.id.rcview);
        ContactAdapter contactAdapter = new ContactAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
       getdata(contactAdapter);
        return view;
    }

    public void getdata(ContactAdapter contactAdapter){
        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
        Cursor cursor = databaseHelper.Getalldata();

        Model model = new Model();
        arrayList.clear();
        while(cursor.moveToNext()){

            //model = new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2));
            arrayList.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }
        // Log.d("TAG", model.toString());
        recyclerView.setAdapter(contactAdapter);
        Log.d("TAG", arrayList.size()+" ");
    }

}
