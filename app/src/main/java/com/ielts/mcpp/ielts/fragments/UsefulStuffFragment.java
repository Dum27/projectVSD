package com.ielts.mcpp.ielts.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;


public class UsefulStuffFragment extends Fragment implements View.OnClickListener {

    View view;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_useful_stuff, container, false);
        ButtonRectangle stayCalm = (ButtonRectangle) view.findViewById(R.id.stayCalm);
        ButtonRectangle testFacts = (ButtonRectangle) view.findViewById(R.id.testFacts);
        ButtonRectangle examRules = (ButtonRectangle) view.findViewById(R.id.examRules);
        ButtonRectangle dos = (ButtonRectangle) view.findViewById(R.id.dos);
        ButtonRectangle donts = (ButtonRectangle) view.findViewById(R.id.donts);
        ButtonRectangle sample = (ButtonRectangle) view.findViewById(R.id.sampleTasks);
        ButtonRectangle examiners = (ButtonRectangle) view.findViewById(R.id.theExaminers);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("");
        stayCalm.setOnClickListener(this);
        testFacts.setOnClickListener(this);
        examRules.setOnClickListener(this);
        dos.setOnClickListener(this);
        donts.setOnClickListener(this);
        sample.setOnClickListener(this);
        examiners.setOnClickListener(this);
        new LoadAds(view, R.id.adViewAbout);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stayCalm:
                fragmentTransaction.replace(R.id.container_stuff, new StayCalmFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.testFacts:
                fragmentTransaction.replace(R.id.container_stuff, new TestFactsFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.examRules:
                fragmentTransaction.replace(R.id.container_stuff, new ExamRulesFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.dos:
                fragmentTransaction.replace(R.id.container_stuff, new DosFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.donts:
                fragmentTransaction.replace(R.id.container_stuff, new DontsFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.sampleTasks:
                fragmentTransaction.replace(R.id.container_stuff, new SampleTasksFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
            case R.id.theExaminers:
                fragmentTransaction.replace(R.id.container_stuff, new TheExaminersFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;
        }
    }
}
