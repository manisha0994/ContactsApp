package com.example.contactsapp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.contactsapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button one, two, three, four, five, six, seven, eight, nine, star, zero, has;
    TextView editno;
    ImageButton call,delete;

    String number = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhoneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhoneFragment newInstance(String param1, String param2) {
        PhoneFragment fragment = new PhoneFragment();
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
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        star = view.findViewById(R.id.star);
        zero = view.findViewById(R.id.zero);
        has = view.findViewById(R.id.has);
        editno = view.findViewById(R.id.editno);
        call = view.findViewById(R.id.call);
        delete = view.findViewById(R.id.delete);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        star.setOnClickListener(this);
        zero.setOnClickListener(this);
        has.setOnClickListener(this);
        call.setOnClickListener(this);
        delete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == one) {
            number = number +"1";
            editno.setText(number);
        }
        if (view == two) {
            number = number +"2";
            editno.setText(number);
        }
        if (view == three) {
            number = number +"3";
            editno.setText(number);
        }
        if (view == four) {
            number = number +"4";
            editno.setText(number);
        }
        if (view == five) {
            number = number +"5";
            editno.setText(number);
        }
        if (view == six) {
            number = number +"6";
            editno.setText(number);
        }
        if (view == seven) {
            number = number +"7";
            editno.setText(number);
        }
        if (view == eight) {
            number = number +"8";
            editno.setText(number);
        }
        if (view == nine) {
            number = number +"9";
            editno.setText(number);
        }
        if (view == star) {
            number = number +"*";
            editno.setText(number);
        }
        if (view == zero) {
            number = number +"0";
            editno.setText(number);
        }
        if (view == has) {
            number = number +"#";
            editno.setText(number);
        }
        if (view == call) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+number));
            startActivity(intent);
        }
        if (view == delete) {
          number = number.substring(0,number.length()-2);
          editno.setText(number);
        }
    }
}
