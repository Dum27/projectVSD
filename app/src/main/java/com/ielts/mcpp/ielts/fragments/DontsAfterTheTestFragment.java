package com.ielts.mcpp.ielts.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;

public class DontsAfterTheTestFragment extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_donts_after_the_test, container, false);
        ((MainActivity) this.getActivity()).setPageTitle("Dont's");
        new LoadAds(view, R.id.adViewDontsAfterTheTest);
        return view;
    }

}