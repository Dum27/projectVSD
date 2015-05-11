package com.ielts.mcpp.ielts.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;

/**
 * Created by taras on 20.04.2015.
 */
public class FeaturesFragment extends Fragment {

    ButtonRectangle mSpeakingTest;
    ButtonRectangle mTestScore;
    ButtonRectangle mScoreReport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_features,null);
        mSpeakingTest = (ButtonRectangle) v.findViewById(R.id.btn_speaking_test);
        mTestScore = (ButtonRectangle) v.findViewById(R.id.btn_test_score);
        mScoreReport = (ButtonRectangle) v.findViewById(R.id.btn_score_report);

        mSpeakingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.about_layer_cont,new SpeakingTestFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mTestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.about_layer_cont,new TestScoreFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mScoreReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.about_layer_cont,new ScoreReportFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        new LoadAds(v, R.id.adViewFeatures);
        return v;
    }
}
