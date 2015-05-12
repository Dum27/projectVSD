package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
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
    private ButtonFloatSmall mMicBtn;
    private ButtonFloat mStopBtn;
    private MediaPlayer mediaPlayer;
    
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
    TextView mTopic;
    TextView mBigText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_test_task, container, false);
//        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat);
        mTimer = (TextView) view.findViewById(R.id.timer);
        mTopic = (TextView) view.findViewById(R.id.topic_test1);
        mBigText = (TextView) view.findViewById(R.id.text_test1);

        mStopBtn.setBackgroundColor(0xFFF36C3B);
        mStopBtn.setRippleColor(0xF8D16F37);
        mStopBtn.setOnClickListener(this);

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("taras", "millis :" + millisUntilFinished + " | " + ((int) millisUntilFinished));

                //audio 5 seconds
                if (299000 < millisUntilFinished) {
                    setBtnRecordingOff();
                    playQuestion(Environment.getExternalStorageDirectory() + File.separator + "questions" + File.separator + "intro-frame-good-morning.mp4");
                }
                if (298000 < millisUntilFinished && millisUntilFinished < 299000) {
                    setBtnRecordingOff();
                    playQuestion(Environment.getExternalStorageDirectory() + File.separator +
                            "questions" + File.separator + "intro-frame-q1.mp4");
                }
                //answer 6 seconds
                if (294000 < millisUntilFinished && millisUntilFinished < 295000) {
                    setBtnRecordingOn();
                    recordAnswer(6000);
                }
                //audio 3 seconds
                if (289000 < millisUntilFinished && millisUntilFinished < 290000) {
                    setBtnRecordingOff();
                    playQuestion(Environment.getExternalStorageDirectory() + File.separator +
                            "questions" + File.separator + "intro-frame-q2.mp4");
                }
                //answer 6 seconds
                if (286000 < millisUntilFinished && millisUntilFinished < 287000) {
                    setBtnRecordingOn();
                    recordAnswer(6000);
                }
                //audio 3 seconds
                if (280000 < millisUntilFinished && millisUntilFinished < 281000) {
                    setBtnRecordingOff();
                    playQuestion(Environment.getExternalStorageDirectory() + File.separator +
                            "questions" + File.separator + "intro-frame-q3.mp4");
                }
                //answer 6 seconds
                if (277000 < millisUntilFinished && millisUntilFinished < 278000) {
                    setBtnRecordingOn();
                    recordAnswer(6000);
                }
                //audio 10 seconds
                if (271000 < millisUntilFinished && millisUntilFinished < 262000) {
                    setBtnRecordingOff();
                    playQuestion(Environment.getExternalStorageDirectory() + File.separator +
                            "questions" + File.separator + "intro-frame-s1.mp4");
                }
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                mTimer.setText(v + ":" + String.format("%02d", va));
            }

            public void onFinish() {
                mTimer.setText("00:00");
            }
        }.start();

        introductoryFrame();

        new LoadAds(view, R.id.adViewFirstTest);
        return view;
    }

    private void setBtnRecordingOn() {
        mMicBtn.setBackgroundColor(0xFFFF3500);
    }

    private void setBtnRecordingOff() {
        mMicBtn.setBackgroundColor(0xafc4c4c4);
    }

    private void introductoryFrame() {
        ((MainActivity) this.getActivity()).setPageTitle("Introductory frame");
        mTopic.setVisibility(View.GONE);
        mBigText.setText("• Listen whilst the examiner introduces the test\n" +
                "• The clock on the right will start to countdown when the test starts");
    }

    private void frame_1() {

    }

    private void frame_2() {

    }

    private void frame_3() {

    }

    private void playQuestion(String fileName) {
        try {
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recordAnswer(long duration) {


    }

    private String getNextFileName() {
        return Environment.getExternalStorageDirectory()
                + File.separator + "Test1.mp4";
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
