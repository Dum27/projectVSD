package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.github.lassana.recorder.Mp4ParserWrapper;
import com.ielts.mcpp.ielts.Constants;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.VocabAdapter;
import com.ielts.mcpp.ielts.constants.Vocabulary;
import com.ielts.mcpp.ielts.utils.CountDownTimerPausable;
import com.ielts.mcpp.ielts.utils.LoadAds;
import com.ielts.mcpp.ielts.utils.PercentView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//import com.github.lassana.recorder.AudioRecorder;

/**
 * Created by taras on 27.04.2015.
 */
public class ThirdTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
    //    private AudioRecorder mAudioRecorder;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;
    private String mBigTextFrame1;
    private String mBigTextFrame2;
    private String mBigTextFrame3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


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

    private String[] mPart1;
    private String[] mPart2;
    private String[] mPart3;

    private View popupView;
    private ListView listViewWords;
    VocabAdapter mVocabAdapter;
    PopupWindow popupWindow;
    PercentView mTimerClock;

    CountDownTimerPausable countDownTimer;

    int delta = 45000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_third_test, container, false);
//        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall3);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat3);
        mTimer = (TextView) view.findViewById(R.id.timer3);
        mTopic = (TextView) view.findViewById(R.id.topic_test3);
        mBigText = (TextView) view.findViewById(R.id.big_text_test3);
        mTimerClock = (PercentView)   view.findViewById(R.id.percentview3);

        mTimerClock.setProgressColor(0xFFF36C3B);
        mTimerClock.setPercentage(0);
        mStopBtn.setBackgroundColor(0xFFDD230D);
        mStopBtn.setRippleColor(0xBFBE220D);
        mStopBtn.setOnClickListener(this);

        popupView = inflater.inflate(R.layout.vocabulary_popup_window, null);
        listViewWords = (ListView) popupView.findViewById(R.id.listViewWords);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ((MainActivity) this.getActivity()).setPageTitle("Part 3");
        ((MainActivity) this.getActivity()).setPageColor(0xFFDD230D, Color.BLACK);

        mCurFileName = getNextFileName();

        mQuestionsPath = Environment.getExternalStorageDirectory()
                + File.separator
                + "questions"
                + File.separator;
        mTestFolderPath = Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator;

        listOfAudio = new ArrayList<>();
        setRandomFrame();
        countDownTimer = new CountDownTimerPausable(300000 + delta, 1000) {


            public void onTick(long millisUntilFinished) {

                //audio 14 seconds
                if (299000 + delta < millisUntilFinished && millisUntilFinished < 300000+ delta) {
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart1[0]);
                    listOfAudio.add(mQuestionsPath + mPart1[0]);
                }

//////////////////////////////////////////////////////////////////////////
//                FRAME 1
//////////////////////////////////////////////////////////////////////////
                if(285000+ delta < millisUntilFinished && millisUntilFinished < 286000+ delta){
                    pauseTimer();
                    showVocabDialog(mBigTextFrame1);
                }
                //question 6 seconds
                if (284000 + delta < millisUntilFinished && millisUntilFinished < 285000 + delta) {
                    Log.d("taras", "FRAME 1!!");
                    part_1();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart1[1]);
                    listOfAudio.add(mTestFolderPath + mPart1[1]);
                }
                //answer 25 seconds
                if (277000 + delta < millisUntilFinished && millisUntilFinished < 278000 + delta) {

                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-home-answ1.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-home-answ1.mp4");
                }
                //question 6 seconds
                if (251000 + delta < millisUntilFinished && millisUntilFinished < 252000 + delta) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart1[2]);
                    listOfAudio.add(mTestFolderPath + mPart1[2]);
                }
                //answer 25 seconds
                if (244000 + delta < millisUntilFinished && millisUntilFinished < 245000 + delta) {
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-home-answ2.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-home-answ2.mp4");
                }
                //question 6 seconds
                if (218000 + delta < millisUntilFinished && millisUntilFinished < 219000 + delta) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart1[3]);
                    listOfAudio.add(mTestFolderPath + mPart1[3]);
                }
                //answer 25 seconds
                if (211000 + delta < millisUntilFinished && millisUntilFinished < 212000 + delta) {
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-home-answ3.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-home-answ3.mp4");
                }
//////////////////////////////////////////////////////////////////////////
//                FRAME 2
//////////////////////////////////////////////////////////////////////////
                if(186000 +delta< millisUntilFinished && millisUntilFinished < 187000 +delta){
                    pauseTimer();
                    showVocabDialog(mBigTextFrame2);
                }
                //question 6 seconds
                if (185000 + delta < millisUntilFinished && millisUntilFinished < 186000 + delta) {
                    recordStop();
                    part_2();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart2[0]);
                    listOfAudio.add(mTestFolderPath + mPart2[0]);
                }
                //question 6 seconds
                if (185000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 186000 + delta - 6000) {
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart2[1]);
                    listOfAudio.add(mTestFolderPath + mPart2[1]);
                }
                //answer 25 seconds
                if (178000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 179000 + delta - 6000) {
                    Log.d("taras", "answer1");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-museums-fr8-answ1.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-museums-fr8-answ1.mp4");
                }
                //question 6 seconds
                if (152000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 153000 + delta - 6000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart2[2]);
                    listOfAudio.add(mTestFolderPath + mPart2[2]);
                }
                //answer 25 seconds
                if (145000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 146000 + delta - 6000) {
                    Log.d("taras", "answer3");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-museums-fr8-answ2.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-museums-fr8-answ2.mp4");
                }
                //question 6 seconds
                if (119000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 120000 + delta - 6000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart2[3]);
                    listOfAudio.add(mTestFolderPath + mPart2[3]);
                }
                //answer 25 seconds
                if (112000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 113000 + delta - 6000) {
                    Log.d("taras", "answer3");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-museums-fr8-answ3.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-museums-fr8-answ3.mp4");
                }


//////////////////////////////////////////////////////////////////////////
//                FRAME 3
//////////////////////////////////////////////////////////////////////////
                if(87000 +delta- 6000< millisUntilFinished && millisUntilFinished < 88000 +delta- 6000){
                    pauseTimer();
                    showVocabDialog(mBigTextFrame3);
                }
                //question 6 seconds
                if (86000 + delta - 6000 < millisUntilFinished && millisUntilFinished < 87000 + delta - 6000) {
                    recordStop();
                    part_3();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart3[0]);
                    listOfAudio.add(mTestFolderPath + mPart3[0]);
                }
                if (86000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 87000 + delta - 12000) {
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart3[1]);
                    listOfAudio.add(mTestFolderPath + mPart3[1]);
                }
                //answer 25 seconds
                if (79000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 80000 + delta - 12000) {
                    Log.d("taras", "answer1");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-weather-fr4-answ1.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-weather-fr4-answ1.mp4");
                }
                //question 6 seconds
                if (53000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 54000 + delta - 12000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart3[2]);
                    listOfAudio.add(mTestFolderPath + mPart3[2]);
                }
                //answer 25 seconds
                if (45000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 46000 + delta - 12000) {
                    Log.d("taras", "answer3");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-weather-fr4-answ2.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-weather-fr4-answ2.mp4");
                }
                //question 6 seconds
                if (19000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 20000 + delta - 12000) {
                    recordStop();
                    setBtnRecordingOff();
                    playQuestion(mQuestionsPath + mPart3[3]);
                    listOfAudio.add(mTestFolderPath + mPart3[3]);
                }
                //answer 25 seconds
                if (12000 + delta - 12000 < millisUntilFinished && millisUntilFinished < 13000 + delta - 12000) {
                    Log.d("taras", "answer3");
                    setBtnRecordingOn();
                    recordStart(mTestFolderPath + "part3-weather-fr4-answ3.mp4");
                    listOfAudio.add(mTestFolderPath + "part3-weather-fr4-answ3.mp4");
                }
                // thanks 12 seconds
                if (-13000 + delta - 12000 < millisUntilFinished && millisUntilFinished < -12000 + delta - 12000) {
                    setBtnRecordingOff();
                    recordStop();
                    playQuestion(mQuestionsPath + Constants.part3EndOfTestFolder[0]);
                    listOfAudio.add(mTestFolderPath + Constants.part3EndOfTestFolder[0]);
                }
                if (-26000 + delta - 12000 < millisUntilFinished && millisUntilFinished < -25000 + delta - 12000) {
                    new MergeTask(getActivity()).execute(listOfAudio);
                }
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                mTimer.setText(v + ":" + String.format("%02d", va));
                long persents = (300000+delta-millisUntilFinished)/((300000+delta)/100);
                Log.d("taras","progress :"+persents);
                mTimerClock.setPercentage(persents);
            }

            public void onFinish() {
                Toast.makeText(getActivity(), "Test finished! Thank you!", Toast.LENGTH_LONG);
            }
        }.start();
        new LoadAds(view, R.id.adViewThirdTest);
        return view;
    }

    private void setBtnRecordingOff() {
        mMicBtn.setBackgroundColor(0xafc4c4c4);
    }

    private void setBtnRecordingOn() {
        mMicBtn.setBackgroundColor(0xFFFF3500);
    }

    private void part_1() {
        ((MainActivity) this.getActivity()).setPageTitle("Part3 - Frame 1");
        mTopic.setVisibility(View.VISIBLE);
        mTopic.setText("Topic");
        mBigText.setText(mBigTextFrame1);
    }

    private void part_2() {
        ((MainActivity) this.getActivity()).setPageTitle("Part3 - Frame 2");
        mTopic.setVisibility(View.VISIBLE);
        mTopic.setText("Topic");
        mBigText.setText(mBigTextFrame2);
    }

    private void part_3() {
        ((MainActivity) this.getActivity()).setPageTitle("Part3 - Frame 3");
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
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
//        String date = new SimpleDateFormat("dd-MM-yy_HH-mm").format(gregorianCalendar.getTime());
        return Environment.getExternalStorageDirectory()
                + File.separator + mTestFolderName
                + File.separator + "merge_T3_" + MainActivity.sTestFileDate
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

    private void setRandomFrame() {

        int rand = MainActivity.sTextTask;

        Log.d("taras", "rand :" + rand);
        switch (rand) {
            case 605:
                mPart1 = Constants.part3Task605_1;
                mPart2 = Constants.part3Task605_2;
                mPart3 = Constants.part3Task605_3;
                mBigTextFrame1 = "Consumer products";
                mBigTextFrame2 = "Online shopping";
                mBigTextFrame3 = "Consumerism and the environment";
                break;
            case 606:
                mPart1 = Constants.part3Task606_1;
                mPart2 = Constants.part3Task606_2;
                mPart3 = Constants.part3Task606_3;
                mBigTextFrame1 = "Family decisions";
                mBigTextFrame2 = "Ways of making decisions";
                mBigTextFrame3 = "International co-operation";
                break;
            case 607:
                mPart1 = Constants.part3Task607_1;
                mPart2 = Constants.part3Task607_2;
                mPart3 = Constants.part3Task607_3;
                mBigTextFrame1 = "Day-to-day planning";
                mBigTextFrame2 = "Planning a career";
                mBigTextFrame3 = "Government planning";
                break;
            case 608:
                mPart1 = Constants.part3Task608_1;
                mPart2 = Constants.part3Task608_2;
                mPart3 = Constants.part3Task608_3;
                mBigTextFrame1 = "Spoken communication skills";
                mBigTextFrame2 = "Learning to speak";
                mBigTextFrame3 = "Language and species";
                break;
            case 609:
                mPart1 = Constants.part3Task609_1;
                mPart2 = Constants.part3Task609_2;
                mPart3 = Constants.part3Task609_3;
                mBigTextFrame1 = "Films about real people and events";
                mBigTextFrame2 = "Film actors";
                mBigTextFrame3 = "The influence of films on audiences";
                break;
            case 610:
                mPart1 = Constants.part3Task610_1;
                mPart2 = Constants.part3Task610_2;
                mPart3 = Constants.part3Task610_3;
                mBigTextFrame1 = "Cars and society";
                mBigTextFrame2 = "Public transport";
                mBigTextFrame3 = "Road networks";
                break;
            case 611:
                mPart1 = Constants.part3Task611_1;
                mPart2 = Constants.part3Task611_2;
                mPart3 = Constants.part3Task611_3;
                mBigTextFrame1 = "How parents help young children";
                mBigTextFrame2 = "The influence of family";
                mBigTextFrame3 = "Learning from old people";
                break;
            case 612:
                mPart1 = Constants.part3Task612_1;
                mPart2 = Constants.part3Task612_2;
                mPart3 = Constants.part3Task612_3;
                mBigTextFrame1 = "Gifts for children";
                mBigTextFrame2 = "Attitudes to gifts";
                mBigTextFrame3 = "Commercial aspects of gift giving";
                break;
            default:
                Log.d("taras", "default");
                mPart1 = Constants.part3Task612_1;
                mPart2 = Constants.part3Task612_2;
                mPart3 = Constants.part3Task612_3;
                mBigTextFrame1 = "Gifts for children";
                mBigTextFrame2 = "Attitudes to gifts";
                mBigTextFrame3 = "Commercial aspects of gift giving";
                break;
        }
        Log.d("taras","frame1 :"+mBigTextFrame1+"\n"+
                "frame2 :"+mBigTextFrame2+"\n"+
                "frame3 :"+mBigTextFrame3);
    }
    private void pauseTimer() {
        countDownTimer.pause();
    }

    private void resumeTimer() {
        countDownTimer.resume();
    }

    private void showVocabDialog(String topic){
        popupWindow.dismiss();
        String Topic = topic;

        TextView title = (TextView) popupView.findViewById(R.id.title);
        title.setText("You need to knew...");

        mVocabAdapter = new VocabAdapter(getActivity(), Vocabulary.getEngWords(Topic),
                Vocabulary.getChiWords(Topic));
        listViewWords.setAdapter(mVocabAdapter);

        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                resumeTimer();
            }
        });
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
    @Override
    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.buttonFloat:
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container, new SecondTestFragment());
//                fragmentTransaction.commit();
//                break;
//            default:
//                break;
//        }
    }
}