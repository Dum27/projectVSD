package com.ielts.mcpp.ielts.fragments;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;


public class LayerTestTaskFragment extends Fragment {


    public LayerTestTaskFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layer_test_task, container, false);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new TestFragment());
        fragmentTransaction.commit();
        return view;
    }


}
