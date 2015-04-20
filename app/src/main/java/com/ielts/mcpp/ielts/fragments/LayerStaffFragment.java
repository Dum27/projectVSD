package com.ielts.mcpp.ielts.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;

public class LayerStaffFragment extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layer_staff, container, false);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.container_stuff,new UsefulStuffFragment());
        ft.commit();
        return view;
    }

}