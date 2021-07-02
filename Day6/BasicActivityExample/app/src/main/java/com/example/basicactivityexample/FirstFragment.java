package com.example.basicactivityexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.basicactivityexample.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView textViewCount;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        textViewCount=binding.textViewFirst;

        binding.buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countIncrease(v);
            }
        });




        return binding.getRoot();

    }
    public void countIncrease(View view){
        String countString=textViewCount.getText().toString();
        Integer count=Integer.parseInt(countString);
        count++;
        textViewCount.setText(count.toString());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countString=textViewCount.getText().toString();
                Integer count=Integer.parseInt(countString);

                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action
                        = FirstFragmentDirections.actionFirstFragmentToSecondFragment(count);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}