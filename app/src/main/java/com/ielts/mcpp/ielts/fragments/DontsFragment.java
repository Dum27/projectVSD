package com.ielts.mcpp.ielts.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;

import it.neokree.materialnavigationdrawer.elements.MaterialSection;

public class DontsFragment extends Fragment implements View.OnClickListener{

    View view;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_donts, container, false);
        ButtonRectangle before = (ButtonRectangle) view.findViewById(R.id.before);
        ButtonRectangle during = (ButtonRectangle) view.findViewById(R.id.during);
        ButtonRectangle after = (ButtonRectangle) view.findViewById(R.id.after);
        before.setOnClickListener(this);
        during.setOnClickListener(this);
        after.setOnClickListener(this);
        fragmentTransaction = getFragmentManager().beginTransaction();
        ((MainActivity) this.getActivity()).setPageTitle("Dont's");
        new LoadAds(view, R.id.adViewDontsTheTest);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.before:
                fragmentTransaction.replace(R.id.container_stuff, new DontsBeforeTheTestFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.during:
                fragmentTransaction.replace(R.id.container_stuff, new DontsBeforeTheTestFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.after:
                fragmentTransaction.replace(R.id.container_stuff, new DontsAfterTheTestFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
        }
    }

}