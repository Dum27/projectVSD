package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;
import com.ielts.mcpp.ielts.utils.LoadInterstitialAds;

import java.io.File;

//import com.github.lassana.recorder.AudioRecorder;


public class FirstTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
    //    private AudioRecorder mAudioRecorder;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;
    LoadInterstitialAds interstitialAds;
    public FirstTestFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialAds = new LoadInterstitialAds(getActivity());
    }


    String currentFileName;
    Handler handler;
    Runnable runnable;
    boolean isRecording = false;
    TextView mTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_test_task, container, false);
//        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat);
        mTimer = (TextView) view.findViewById(R.id.timer);
        mStopBtn.setBackgroundColor(0xFFF36C3B);
        mStopBtn.setRippleColor(0xF8D16F37);
        mStopBtn.setOnClickListener(this);
        ((MainActivity) this.getActivity()).setPageTitle("Part 1");
        runnable = new Runnable() {
            @Override
            public void run() {
                changeColor();
                handler.postDelayed(this, 2000);
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 100);
        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                mTimer.setText(v + ":" + String.format("%02d", va));
            }

            public void onFinish() {
                mTimer.setText("00:00");
            }
        }.start();
        new LoadAds(view, R.id.adViewFirstTest);
        return view;
    }

    private void changeColor() {
        if (isRecording) {
            mMicBtn.setBackgroundColor(0xFFFF3500);
            isRecording = false;
        } else {
            mMicBtn.setBackgroundColor(0xafc4c4c4);
            isRecording = true;
        }

    }

    private void during_the_test() {

    }

    private void during_the_intro() {

    }

    private void introductory_frame() {

    }

    private void playQuestion(String fileName) {

        File file = new File(fileName);
        if (file.exists()) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            startActivity(intent);
        }
    }

    private void recordAnswer(long duration) {


    }

    private String getNextFileName() {
        return Environment.getExternalStorageDirectory()
                + File.separator + "Test1.mp4";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new SecondTestFragment());
                fragmentTransaction.commit();
                interstitialAds.show();
                break;
            default:
                break;
        }
    }
}
