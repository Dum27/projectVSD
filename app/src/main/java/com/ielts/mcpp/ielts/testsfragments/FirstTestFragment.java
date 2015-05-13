package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.github.lassana.recorder.Mp4ParserWrapper;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class FirstTestFragment extends Fragment implements View.OnClickListener {

    private ButtonFloatSmall mMicBtn;
    private ButtonFloat mStopBtn;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String mTestFolderName = "ielts_tests";
    private String mTestFolderPath;
    private String mQuestionsPath;
    private String mCurFileName;

    private TextView mTimer;
    private TextView mTopic;
    private TextView mBigText;

    ArrayList<String> listOfAudio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_test_task, container, false);
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat);
        mTimer = (TextView) view.findViewById(R.id.timer);
        mTopic = (TextView) view.findViewById(R.id.topic_test1);
        mBigText = (TextView) view.findViewById(R.id.text_test1);

        mStopBtn.setBackgroundColor(0xFFF36C3B);
        mStopBtn.setRippleColor(0xF8D16F37);
        mStopBtn.setOnClickListener(this);

        mCurFileName = getNextFileName();

        mQuestionsPath = Environment.getExternalStorageDirectory()
                + File.separator
                + "questions"
                + File.separator;
        mTestFolderPath = Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator;

        listOfAudio = new ArrayList<>();

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {

                //audio 5 seconds
                if (299000 < millisUntilFinished) {
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + "intro-frame-good-morning.mp4");
                    listOfAudio.add(mQuestionsPath + "intro-frame-good-morning.mp4");
                }
                if (298000 < millisUntilFinished && millisUntilFinished < 299000) {
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + "intro-frame-q1.mp4");
                    listOfAudio.add(mQuestionsPath + "intro-frame-q1.mp4");
                }
                //answer 6 seconds
                if (294000 < millisUntilFinished && millisUntilFinished < 295000) {
                    recordStart(mTestFolderPath + "intro-frame-answ1.mp4");
                    setBtnRecordingOn();
                    listOfAudio.add(mTestFolderPath + "intro-frame-answ1.mp4");
                }
                //audio 3 seconds
                if (289000 < millisUntilFinished && millisUntilFinished < 290000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + "intro-frame-q2.mp4");
                    listOfAudio.add(mQuestionsPath + "intro-frame-q2.mp4");
                }
                //answer 6 seconds
                if (286000 < millisUntilFinished && millisUntilFinished < 287000) {
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "intro-frame-answ2.mp4");
                    listOfAudio.add(mTestFolderPath + "intro-frame-answ2.mp4");
                }
                //audio 3 seconds
                if (280000 < millisUntilFinished && millisUntilFinished < 281000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + "intro-frame-q3.mp4");
                    listOfAudio.add(mQuestionsPath + "intro-frame-q3.mp4");
                }
                //answer 6 seconds
                if (277000 < millisUntilFinished && millisUntilFinished < 278000) {
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "intro-frame-answ3.mp4");
                    listOfAudio.add(mTestFolderPath + "intro-frame-answ3.mp4");
                }
                //audio 10 seconds
                if (271000 < millisUntilFinished && millisUntilFinished < 272000) {
                    recordStop();
                    setBtnRecordingOff();
//                    playQuestion(mQuestionsPath + "intro-frame-s1.mp4");
                    new MergeTask(getActivity()).execute(listOfAudio);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat:
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new SecondTestFragment());
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
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
