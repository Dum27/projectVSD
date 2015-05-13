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
 * Created by taras on 06.05.2015.
 */
public class IntroFragment extends Fragment implements View.OnClickListener {
    TextView mTitle;
    TextView mBigText;
    ButtonFloat mNextBtn;
    boolean during_the_test = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introductory_test, null);

//        AdView mAdView = (AdView) view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        mTitle = (TextView) view.findViewById(R.id.intro_title);
        mBigText = (TextView) view.findViewById(R.id.intro_text);
        mNextBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat_intro);
        mNextBtn.setBackgroundColor(0xFFF36C3B);
        mNextBtn.setRippleColor(0xF8D16F37);
        mNextBtn.setOnClickListener(this);

        ((MainActivity) this.getActivity()).setPageColor(0xFFF36C3B, Color.BLACK);
        duringTheTest();
        new LoadAds(view, R.id.adViewIntroTest);
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
                    fragmentTransaction.replace(R.id.container, new FirstTestFragment());
                    fragmentTransaction.commit();
                }
                break;
            default:
                break;
        }
    }

    private void duringTheTest() {
        ((MainActivity) this.getActivity()).setPageTitle("During the test");
        mTitle.setText("Make sure");
        mBigText.setText(
                "• The place you are in is quiet so that the sound recording is good!\n" +
                        "• You are using earphones\n" +
                        "• You have enough power on your device\n" +
                        "• You have enough time? You will need at least 20 minutes!\n" +
                        "• You are connected to Wi-Fi\n ");
    }

    private void duringTheIntro() {
        ((MainActivity) this.getActivity()).setPageTitle("During the Intro");
        mTitle.setText("What happens now?");
        mBigText.setText(
                "• Once your press 'Start' you will hear an examiner introducing the test\n" +
                        "• Listen carefully and answer the questions asked\n" +
                        "• Press the 'Done' button when you have finished speaking\n" +
                        "• The test will be recorded so you can send it for marking\n" +
                        "• Good luck!");

    }
}
