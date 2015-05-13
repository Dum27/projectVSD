package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;

/**
 * Created by taras on 13.05.2015.
 */
public class IntroThirdTestFragment extends Fragment implements View.OnClickListener {
    TextView mTitle;
    TextView mBigText;
    ButtonFloat mNextBtn;
    boolean during_the_test = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introductory_test3, null);

//        AdView mAdView = (AdView) view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        mTitle = (TextView) view.findViewById(R.id.intro_title_test3);
        mBigText = (TextView) view.findViewById(R.id.intro_text_test3);
        mNextBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat_intro_test3);
        mNextBtn.setBackgroundColor(0xFFDD230D);
        mNextBtn.setRippleColor(0xBFBE220D);
        mNextBtn.setOnClickListener(this);

        ((MainActivity) this.getActivity()).setPageColor(0xFFF36C3B, Color.BLACK);
        duringTheTest();
        new LoadAds(view, R.id.adViewIntroTest_test3);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat_intro:
                if (during_the_test) {
                    duringTheIntro();
                    during_the_test = false;
                } else {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ThirdTestFragment());
                    fragmentTransaction.commit();
                }
                break;
            default:
                break;
        }
    }

    private void duringTheTest() {
        ((MainActivity) this.getActivity()).setPageTitle("Part 3");
        mTitle.setText("Make sure");
        mBigText.setText(
                "• You answer the questions fully and to the best of your ability\n" +
                        "• You listen very carefully\n" +
                        "• You speak as fluently and clearly as you can\n");
    }

    private void duringTheIntro() {
        ((MainActivity) this.getActivity()).setPageTitle("During Part 3");
        mTitle.setText("What happens now?");
        mBigText.setText(
                "• The examiner will ask you between 6 and 9 questions on 2 or 3 topics depending on your language level\n" +
                        "• The topics in Part 3 are all related to the topic in Part 2\n" +
                        "• Good luck and remember to breathe!\n");

    }
}