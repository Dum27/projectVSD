package com.ielts.mcpp.ielts.registration;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdNoCheckFragment extends Fragment {

    View view;

    public ThirdNoCheckFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_no_check, container, false);
        return view;
    }


}