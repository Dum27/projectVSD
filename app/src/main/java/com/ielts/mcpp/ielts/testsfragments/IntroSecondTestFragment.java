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
public class IntroSecondTestFragment extends Fragment implements View.OnClickListener {
    TextView mTitle;
    TextView mBigText;
    ButtonFloat mNextBtn;
    boolean during_the_test = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introductory_test2, null);

//        AdView mAdView = (AdView) view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        mTitle = (TextView) view.findViewById(R.id.intro_title_test2);
        mBigText = (TextView) view.findViewById(R.id.intro_text_test2);
        mNextBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat_intro_test2);
        mNextBtn.setBackgroundColor(0xFFA4C904);
        mNextBtn.setRippleColor(0xFF98B606);
        mNextBtn.setOnClickListener(this);

        ((MainActivity) this.getActivity()).setPageColor(0xFFA4C904, Color.BLACK);
        duringTheTest();
        new LoadAds(view, R.id.adViewIntroTest_test2);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat_intro_test2:
                if (during_the_test) {
                    duringTheIntro();
                    during_the_test = false;
                } else {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, new SecondTestFragment());
                    fragmentTransaction.commit();
                }
                break;
            default:
                break;
        }
    }

    private void duringTheTest() {
        ((MainActivity) this.getActivity()).setPageTitle("Part 2");
        mTitle.setText("Make sure");
        mBigText.setText(
                "• You have a pencil or pen and some paper in front you\n" +
                        "• You make a plan - see spidergram\n" +
                        "• Watch the clock - you have only 1 minute to prepare \n" +
                        "• Talk about the topic and NOT something you have memorised\n");
    }

    private void duringTheIntro() {
        ((MainActivity) this.getActivity()).setPageTitle("During Part 3");
        mTitle.setText("What happens now?");
        mBigText.setText(
                "• Once you press 'Start' you will hear an examiner introducing Part 2\n" +
                        "• The topic will appear on screen. Read it carefully and quickly\n" +
                        "• You must speak for 1 minute minimum and maximum of 2\n" +
                        "• You can press the done button after one minute if you wish\n" +
                        "• Good luck!\n");
    }
}