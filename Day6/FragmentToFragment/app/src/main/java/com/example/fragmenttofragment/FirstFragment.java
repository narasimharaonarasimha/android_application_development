package com.example.fragmenttofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FirstFragment extends Fragment {

   EditText name;
   Button send;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        name=(EditText) view.findViewById(R.id.editTextName);
        send=(Button) view.findViewById(R.id.buttonSendData);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();

                Bundle bundle=new Bundle();
                bundle.putString("username", userName);
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                SecondFragment secondFragment=new SecondFragment();
                secondFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameLayoutSecond, secondFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}