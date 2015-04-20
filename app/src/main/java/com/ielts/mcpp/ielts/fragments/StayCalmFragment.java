package com.ielts.mcpp.ielts.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;

public class StayCalmFragment extends Fragment implements View.OnClickListener{

    View view;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stay_calm, container, false);
        ButtonRectangle breathing = (ButtonRectangle) view.findViewById(R.id.breathing);
        ButtonRectangle progressive = (ButtonRectangle) view.findViewById(R.id.progressive);
        breathing.setOnClickListener(this);
        progressive.setOnClickListener(this);
        fragmentTransaction = getFragmentManager().beginTransaction();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.breathing:
                fragmentTransaction.replace(R.id.container_stuff, new BreathingFragment());
                fragmentTransaction.commit();
                break;
            case R.id.progressive:
                fragmentTransaction.replace(R.id.container_stuff, new ProgressiveFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}