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
import com.ielts.mcpp.ielts.Constants;
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
import java.util.Random;

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

    String[] mCompulsoryFrame;
    String[] mAdditionalFrame2;
    LoadInterstitialAds interstitialAds;
    String[] mAdditionalFrame3;

    ArrayList<String> listOfAudio;

    private String mBigTextFrame1;
    private String mBigTextFrame2;
    private String mBigTextFrame3;

    CountDownTimer countDownTimer;
    boolean isFinished = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialAds = new LoadInterstitialAds(getActivity());
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

        mCompulsoryFrame = setRandomCompulsoryFrame();
        mAdditionalFrame2 = setRandomAdditionalFrame2();
        mAdditionalFrame3 = setRandomAdditionalFrame3();

        mQuestionsPath = Environment.getExternalStorageDirectory()
                + File.separator
                + "questions"
                + File.separator;
        mTestFolderPath = Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator;

        listOfAudio = new ArrayList<>();

        countDownTimer = new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (!isFinished) {
                    //audio 5 seconds
                    if (299000 < millisUntilFinished) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-good-morning.mp4");
                        listOfAudio.add(mQuestionsPath + "intro-frame-good-morning.mp4");
                    }
                    if (297000 < millisUntilFinished && millisUntilFinished < 298000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-s1.mp4");
                        listOfAudio.add(mQuestionsPath + "intro-frame-s1.mp4");
                    }
                    if (295000 < millisUntilFinished && millisUntilFinished < 296000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-q1.mp4");
                        listOfAudio.add(mQuestionsPath + "intro-frame-q1.mp4");
                    }
                    //answer 6 seconds
                    if (291000 < millisUntilFinished && millisUntilFinished < 292000) {
                        recordStart(mTestFolderPath + "intro-frame-answ1.mp4");
                        setBtnRecordingOn();
                        listOfAudio.add(mTestFolderPath + "intro-frame-answ1.mp4");
                    }
                    //audio 3 seconds
                    if (285000 < millisUntilFinished && millisUntilFinished < 286000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-q2.mp4");
                        listOfAudio.add(mQuestionsPath + "intro-frame-q2.mp4");
                    }
                    //answer 6 seconds
                    if (282000 < millisUntilFinished && millisUntilFinished < 283000) {
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "intro-frame-answ2.mp4");
                        listOfAudio.add(mTestFolderPath + "intro-frame-answ2.mp4");
                    }
                    //audio 3 seconds
                    if (276000 < millisUntilFinished && millisUntilFinished < 277000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-q3.mp4");
                        listOfAudio.add(mQuestionsPath + "intro-frame-q3.mp4");
                    }
                    //answer 6 seconds
                    if (272000 < millisUntilFinished && millisUntilFinished < 273000) {
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "intro-frame-answ3.mp4");
                        listOfAudio.add(mTestFolderPath + "intro-frame-answ3.mp4");
                    }
                    //audio 10 seconds
                    if (266000 < millisUntilFinished && millisUntilFinished < 267000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-q4.mp4");
                    }
                    if (262000 < millisUntilFinished && millisUntilFinished < 263000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "intro-frame-s2.mp4");
                        listOfAudio.add(mTestFolderPath + "intro-frame-s2.mp4");
                    }
//////////////////////////////////////////////////////////////////////////
//                FRAME 1
//////////////////////////////////////////////////////////////////////////
                    //question 3 seconds
                    if (255000 < millisUntilFinished && millisUntilFinished < 256000) {
                        Log.d("taras", "FRAME 1!!");
                        frame_1();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mCompulsoryFrame[0]);
                        listOfAudio.add(mTestFolderPath + mCompulsoryFrame[0]);
                    }
                    //question 3 seconds
                    if (251000 < millisUntilFinished && millisUntilFinished < 252000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mCompulsoryFrame[1]);
                        listOfAudio.add(mTestFolderPath + mCompulsoryFrame[1]);
                    }
                    //answer 18 seconds
                    if (248000 < millisUntilFinished && millisUntilFinished < 249000) {

                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-home-answ1.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-home-answ1.mp4");
                    }
                    //question 5 seconds
                    if (228000 < millisUntilFinished && millisUntilFinished < 229000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mCompulsoryFrame[2]);
                        listOfAudio.add(mTestFolderPath + mCompulsoryFrame[2]);
                    }
                    //answer 18 seconds
                    if (222000 < millisUntilFinished && millisUntilFinished < 223000) {
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-home-answ2.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-home-answ2.mp4");
                    }
                    //question 6 seconds
                    if (203000 < millisUntilFinished && millisUntilFinished < 204000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mCompulsoryFrame[3]);
                        listOfAudio.add(mTestFolderPath + mCompulsoryFrame[3]);
                    }
                    //answer 18 seconds
                    if (196000 < millisUntilFinished && millisUntilFinished < 197000) {
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-home-answ3.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-home-answ3.mp4");
                    }
//////////////////////////////////////////////////////////////////////////
//                FRAME 2
//////////////////////////////////////////////////////////////////////////
                    //question 6 seconds
                    if (177000 < millisUntilFinished && millisUntilFinished < 178000) {
                        recordStop();
                        frame_2();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame2[0]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame2[0]);
                    }
                    //question 6 seconds
                    if (170000 < millisUntilFinished && millisUntilFinished < 171000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame2[1]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame2[1]);
                    }
                    //answer 18 seconds
                    if (163000 < millisUntilFinished && millisUntilFinished < 164000) {
                        Log.d("taras", "answer1");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-museums-fr8-answ1.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-museums-fr8-answ1.mp4");
                    }
                    //question 6 seconds
                    if (144000 < millisUntilFinished && millisUntilFinished < 145000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame2[2]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame2[2]);
                    }
                    //answer 18 seconds
                    if (137000 < millisUntilFinished && millisUntilFinished < 138000) {
                        Log.d("taras", "answer3");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-museums-fr8-answ2.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-museums-fr8-answ2.mp4");
                    }
                    //question 6 seconds
                    if (118000 < millisUntilFinished && millisUntilFinished < 119000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame2[3]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame2[3]);
                    }
                    //answer 18 seconds
                    if (110000 < millisUntilFinished && millisUntilFinished < 111000) {
                        Log.d("taras", "answer3");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-museums-fr8-answ3.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-museums-fr8-answ3.mp4");
                    }


//////////////////////////////////////////////////////////////////////////
//                FRAME 3
//////////////////////////////////////////////////////////////////////////
                    //question 6 seconds
                    if (92000 < millisUntilFinished && millisUntilFinished < 93000) {
                        recordStop();
                        frame_3();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame3[0]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame3[0]);
                    }
                    //question 6 seconds
                    if (85000 < millisUntilFinished && millisUntilFinished < 86000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame3[1]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame3[1]);
                    }
                    //answer 18 seconds
                    if (78000 < millisUntilFinished && millisUntilFinished < 79000) {
                        Log.d("taras", "answer1");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-weather-fr4-answ1.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-weather-fr4-answ1.mp4");
                    }
                    //question 6 seconds
                    if (59000 < millisUntilFinished && millisUntilFinished < 60000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame3[2]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame3[2]);
                    }
                    //answer 18 seconds
                    if (52000 < millisUntilFinished && millisUntilFinished < 53000) {
                        Log.d("taras", "answer3");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-weather-fr4-answ2.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-weather-fr4-answ2.mp4");
                    }
                    //question 6 seconds
                    if (33000 < millisUntilFinished && millisUntilFinished < 34000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + mAdditionalFrame3[3]);
                        listOfAudio.add(mTestFolderPath + mAdditionalFrame3[3]);
                    }
                    //answer 18 seconds
                    if (26000 < millisUntilFinished && millisUntilFinished < 27000) {
                        Log.d("taras", "answer3");
                        setBtnRecordingOn();
                        recordStart(mTestFolderPath + "part1-weather-fr4-answ3.mp4");
                        listOfAudio.add(mTestFolderPath + "part1-weather-fr4-answ3.mp4");
                    }
                    if (7000 < millisUntilFinished && millisUntilFinished < 8000) {

                        setBtnRecordingOff();
                        recordStop();
                        new MergeTask(getActivity()).execute(listOfAudio);
                    }
                    String v = String.format("%02d", millisUntilFinished / 60000);
                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                    mTimer.setText(v + ":" + String.format("%02d", va));
                }
            }

            public void onFinish() {
                mTimer.setText("00:00");
            }
        };
        countDownTimer.start();

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
        mBigText.setText(
                "• Listen whilst the examiner introduces the test\n" +
                        "• The clock on the right will start to countdown when the test starts");
    }


    private void frame_1() {
        ((MainActivity) this.getActivity()).setPageTitle("Part1 - Frame 1");
        mTopic.setVisibility(View.VISIBLE);
        mTopic.setText("Topic");
        mBigText.setText(mBigTextFrame1);
    }

    private void frame_2() {
        ((MainActivity) this.getActivity()).setPageTitle("Part1 - Frame 2");
        mTopic.setVisibility(View.VISIBLE);
        mTopic.setText("Topic");
        mBigText.setText(mBigTextFrame2);
    }

    private void frame_3() {
        ((MainActivity) this.getActivity()).setPageTitle("Part1 - Frame 3");
        mTopic.setVisibility(View.VISIBLE);
        mTopic.setText("Topic");
        mBigText.setText(mBigTextFrame3);
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
        MainActivity.sTestFileDate = date;
        return Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator + "merge_T1_" + date
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
            progressDialog.setCancelable(false);
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
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new SecondTestFragment());
            fragmentTransaction.commit();
        }
    }

    private void appendToFile(final String targetFileName, final String newFileName) {
        Mp4ParserWrapper.append(targetFileName, newFileName);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat:
                releasePlayer();
                releaseRecorder();
                isFinished = true;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new SecondTestFragment());
                fragmentTransaction.commit();
                interstitialAds.show();
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

    private String[] setRandomCompulsoryFrame() {
        String[] result = null;
        Random random = new Random();
        int rand = random.nextInt(6) + 1;

        Log.d("taras", "rand :" + rand);
        switch (rand) {
            case 1:
                result = Constants.part1Frame1Home;
                mBigTextFrame1 = "Home";
                break;
            case 2:
                result = Constants.part1Frame1Home2;
                mBigTextFrame1 = "Home";
                break;
            case 3:
                result = Constants.part1Frame2Study;
                mBigTextFrame1 = "Study";
                break;
            case 4:
                result = Constants.part1Frame2Study2;
                mBigTextFrame1 = "Study";
                break;
            case 5:
                result = Constants.part1Frame2Work;
                mBigTextFrame1 = "Work";
                break;
            case 6:
                result = Constants.part1Frame2Work2;
                mBigTextFrame1 = "Work";
                break;
            default:
                Log.d("taras", "default");
                result = Constants.part1Frame1Home;
                mBigTextFrame1 = "Home";
                break;
        }
        return result;
    }

    private String[] setRandomAdditionalFrame2() {
        String[] result = null;
        int rand = new Random().nextInt(6) + 1;
        switch (rand) {
            case 1:
                result = Constants.part1Frame3PlacesOfEntertainment;
                mBigTextFrame2 = "Places of Entertainment";
                break;
            case 2:
                result = Constants.part1Frame4Weather;
                mBigTextFrame2 = "Weather";
                break;
            case 3:
                result = Constants.part1Frame5Internet;
                mBigTextFrame2 = "Internet";
                break;
            case 4:
                result = Constants.part1Frame6Cards;
                mBigTextFrame2 = "Cards";
                break;
            case 5:
                result = Constants.part1Frame7Cards;
                mBigTextFrame2 = "Cards";
                break;
            case 6:
                result = Constants.part1Frame8Museums;
                mBigTextFrame2 = "Museums";
                break;
            default:
                result = Constants.part1Frame3PlacesOfEntertainment;
                mBigTextFrame2 = "Places of Entertainment";
                break;
        }
        return result;
    }

    private String[] setRandomAdditionalFrame3() {
        String[] result = null;
        int rand = new Random().nextInt(6) + 1;
        switch (rand) {
            case 1:
                result = Constants.part1Frame3PlacesOfEntertainment;
                mBigTextFrame3 = "Places of Entertainment";
                break;
            case 2:
                result = Constants.part1Frame4Weather;
                mBigTextFrame3 = "Weather";
                break;
            case 3:
                result = Constants.part1Frame5Internet;
                mBigTextFrame3 = "Internet";
                break;
            case 4:
                result = Constants.part1Frame6Cards;
                mBigTextFrame3 = "Cards";
                break;
            case 5:
                result = Constants.part1Frame7Cards;
                mBigTextFrame3 = "Cards";
                break;
            case 6:
                result = Constants.part1Frame8Museums;
                mBigTextFrame3 = "Museums";
                break;
            default:
                result = Constants.part1Frame3PlacesOfEntertainment;
                mBigTextFrame3 = "Places of Entertainment";
                break;
        }
        // check for not using the same audios as in additional_frame1
        if (result[0].equals(mAdditionalFrame2[0])) {
            return setRandomCompulsoryFrame();
        }
        return result;
    }
}
