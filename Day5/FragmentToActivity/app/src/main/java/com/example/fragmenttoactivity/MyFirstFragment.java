package com.example.fragmenttoactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MyFirstFragment extends Fragment {

   EditText name, email;
   Button send;

    public MyFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_first, container, false);

        name=(EditText) view.findViewById(R.id.editTextName);
        email= (EditText) view.findViewById(R.id.editTextEmailAddress);
        send=(Button) view.findViewById(R.id.buttonSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                String userEmail=email.getText().toString();
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.collectData(userName,userEmail);
            }
        });
        return view;
    }
}