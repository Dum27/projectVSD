package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.utils.LoadAds;
import com.ielts.mcpp.ielts.utils.LoadInterstitialAds;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//import com.github.lassana.recorder.AudioRecorder;

/**
 * Created by taras on 27.04.2015.
 */
public class SecondTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
    //    private AudioRecorder mAudioRecorder;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;
    LoadInterstitialAds interstitialAds;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String mTestFolderName = "ielts_tests";
    private String mTestFolderPath;
    private String mQuestionsPath;
    private String mCurFileName;

    private TextView mTimer;
    private TextView mTopic;
    private TextView mBigText;



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
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                mTimer.setText(v + ":" + String.format("%02d", va));
            }

            public void onFinish() {
                mTimer.setText("00:00");
            }
        }.start();
        new LoadAds(view, R.id.adViewSecondTest);
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

    private void test1() {

    }

    private void recordAnswer(long duration) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat2:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new ThirdTestFragment());
                fragmentTransaction.commit();
                interstitialAds.show();
                break;
            default:
                break;
        }
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
        mBigText.setText(
                "• Listen whilst the examiner introduces the test\n" +
                        "• The clock on the right will start to countdown when the test starts");
    }
    //

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
    private String getNextFileName() {
        //create directory with test if it does not exist
        File fileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //build filename
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
        String date = new SimpleDateFormat("dd-MM-yy_HH-mm").format(gregorianCalendar.getTime());
        return Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator + date
                + ".mp4";
    }

    private class MergeTask extends AsyncTask<List<String>, Void, Void> {

        ProgressDialog progressDialog;
        Context context;

        private MergeTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(List<String>... lists) {
            ArrayList<String> list = (ArrayList<String>) lists[0];
            for (String fileName : list) {
                Log.d("taras", "merge :" + mCurFileName + "+" + fileName);
                appendToFile(mCurFileName, fileName);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }

    private void appendToFile(final String targetFileName, final String newFileName) {
        Mp4ParserWrapper.append(targetFileName, newFileName);
    }
    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
        releaseRecorder();
    }

    private void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    public void recordStart(String fileName) {
        try {
            releaseRecorder();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioEncodingBitRate(64 * 1024);
            mediaRecorder.setAudioSamplingRate(22050);
            mediaRecorder.setAudioChannels(2);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordStop() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
        }
    }
}