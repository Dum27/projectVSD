package com.ielts.mcpp.ielts.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;


public class SettingsFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
//        new LoadAds(view, R.id.adViewSettings);
    }


    public void onButtonPressed(Uri uri) {

    }
}
