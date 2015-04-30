package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
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
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;

import java.io.File;

/**
 * Created by taras on 27.04.2015.
 */
public class SecondTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
//    private AudioRecorder mAudioRecorder;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;


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
        view = inflater.inflate(R.layout.fragment_second_test, container, false);
//        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall2);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat2);
        mTimer = (TextView) view.findViewById(R.id.timer2);
        mStopBtn.setBackgroundColor(0xFFA4C904);
        mStopBtn.setRippleColor(0xFF98B606);
        mStopBtn.setOnClickListener(this);
        ((MainActivity) this.getActivity()).setPageTitle("Part 2");
        ((MainActivity) this.getActivity()).setPageColor(0xFFA4C904, Color.BLACK);
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
            case R.id.buttonFloat2:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new ThirdTestFragment());
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}