package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.github.lassana.recorder.AudioRecorder;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;

import java.io.File;


public class FirstTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
    private AudioRecorder mAudioRecorder;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;

    public FirstTestFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    String currentFileName;
    Handler handler;
    Runnable runnable;
    boolean isRecording=false;
    TextView mTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_test_task, container, false);
        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat);
        mTimer = (TextView) view.findViewById(R.id.timer);
        mStopBtn.setBackgroundColor(0xFFF36C3B);
        mStopBtn.setRippleColor(0xF8D16F37);
        mStopBtn.setOnClickListener(this);
        ((MainActivity) this.getActivity()).setPageTitle("Part 1");
        ((MainActivity) this.getActivity()).setPageColor(0xFFF36C3B,Color.BLACK);
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
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                mTimer.setText(v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                mTimer.setText("00:00");
            }
        }.start();
        return view;
    }
    private void changeColor(){
        if(isRecording) {
            mMicBtn.setBackgroundColor(0xFFFF3500);
            isRecording = false;
        }
        else {
            mMicBtn.setBackgroundColor(0xafc4c4c4);
            isRecording = true;
        }

    }
    private void test1(){

    }
    private void playQuestion(String fileName){

        File file = new File(fileName);
        if ( file.exists() ) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            startActivity(intent);
        }
    }
    private void recordAnswer(long duration){


    }
    private String getNextFileName() {
        return Environment.getExternalStorageDirectory()
                + File.separator+"Test1.mp4";
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonFloat:
                FragmentTransaction  fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new SecondTestFragment());
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}
