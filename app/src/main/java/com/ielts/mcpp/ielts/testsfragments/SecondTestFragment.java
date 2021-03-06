package com.ielts.mcpp.ielts.testsfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.gc.materialdesign.widgets.Dialog;
import com.github.lassana.recorder.Mp4ParserWrapper;
import com.ielts.mcpp.ielts.Constants;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.adapters.VocabAdapter;
import com.ielts.mcpp.ielts.constants.Vocabulary;
import com.ielts.mcpp.ielts.utils.CountDownTimerPausable;
import com.ielts.mcpp.ielts.utils.LoadAds;
import com.ielts.mcpp.ielts.utils.LoadInterstitialAds;
import com.ielts.mcpp.ielts.utils.PercentView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import com.github.lassana.recorder.AudioRecorder;

/**
 * Created by taras on 27.04.2015.
 */
public class SecondTestFragment extends Fragment implements View.OnClickListener {
    int numberTest = 1;
    ButtonFloatSmall mMicBtn;
    ButtonFloat mStopBtn;
    LoadInterstitialAds interstitialAds;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private String mTestFolderName = "ielts_tests";
    private String mTestFolderPath = Environment.getExternalStorageDirectory() + "/ielts_tests";
    private String mQuestionsPath = Environment.getExternalStorageDirectory() + "/questions/";
    private String mCurFileName;

    private TextView mTimer;
    private TextView mTopic;
    private TextView mBigText;

    Handler handler;
    Runnable runnable;
    boolean isRecording = false;
    boolean isFinish = false;
    ArrayList<String> listOfAudio;

    String[] longTurn;
    private String mBigTextFrame;
    private String mTopicText;

    VocabAdapter mVocabAdapter;
    PopupWindow popupWindow;
    private View popupView;
    private ListView listViewWords;
    private CountDownTimerPausable countDownTimer;
    private String mTopicName;
    PercentView mTimerClock;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialAds = new LoadInterstitialAds(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_second_test, container, false);
//        mAudioRecorder = AudioRecorder.build(getActivity(), getNextFileName());
        mMicBtn = (ButtonFloatSmall) view.findViewById(R.id.buttonFloatSmall2);
        mStopBtn = (ButtonFloat) view.findViewById(R.id.buttonFloat2);
        mTimer = (TextView) view.findViewById(R.id.timer2);
        mTopic = (TextView) view.findViewById(R.id.topic_text);
        mBigText = (TextView) view.findViewById(R.id.text_test2);
        mTimerClock = (PercentView)   view.findViewById(R.id.percentview2);
        mStopBtn.setBackgroundColor(0xFFA4C904);
        mStopBtn.setRippleColor(0xFF98B606);
        mStopBtn.setOnClickListener(this);
        mCurFileName = getNextFileName();

        mTimerClock.setProgressColor(0xFFA4C904);
        mTimerClock.setPercentage(0);

        ((MainActivity) this.getActivity()).setPageTitle("Part 2");
        ((MainActivity) this.getActivity()).setPageColor(0xFFA4C904, Color.BLACK);
        listOfAudio = new ArrayList<>();
        handler = new Handler();
        handler.postDelayed(runnable, 100);
        final int timeToTest = 30000;
        final int delta = 1000;

        popupView = inflater.inflate(R.layout.vocabulary_popup_window, null);
        listViewWords = (ListView) popupView.findViewById(R.id.listViewWords);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        longTurn = setRandomFrame();

        preparation();

        countDownTimer = new CountDownTimerPausable(timeToTest+1000, 1000) {
            int timeDelta = timeToTest;

            public void onTick(long millisUntilFinished) {
                if (!isFinish) {
                    if(timeToTest < millisUntilFinished && millisUntilFinished < timeToTest+1000){
                        pauseTimer();
                        showVocabDialog(mTopicName);
                    }
                    //Intro 1   14 seconds
                    if (timeToTest - 1000 < millisUntilFinished && millisUntilFinished < timeToTest) {
                        Log.d("Jack", String.valueOf(timeDelta));
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "part2-intro.mp4");
                        listOfAudio.add(mQuestionsPath + "part2-intro.mp4");
                    }
                    //Intro 2   8 seconds
                    if (timeToTest - 16000 < millisUntilFinished && millisUntilFinished < timeToTest - 15000) {
                        timeDelta -= 16000;
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "part2-intro-2.mp4");
                        listOfAudio.add(mQuestionsPath + "part2-intro-2.mp4");
                        Log.d("Jack", String.valueOf(timeDelta));
                    }
                    //Intro Unical   8 seconds
                    if (timeToTest - 16000 - 9000 < millisUntilFinished && millisUntilFinished < timeToTest - 16000 - 8000) {
                        timeDelta -= 16000;
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + longTurn[0]);
                        listOfAudio.add(mQuestionsPath + longTurn[0]);
                        Log.d("Jack", String.valueOf(timeDelta));
                    }

                    String v = String.format("%02d", millisUntilFinished / 60000);
                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                    mTimer.setText(v + ":" + String.format("%02d", va));
                    long persents = (30000-millisUntilFinished)/300;
                    mTimerClock.setPercentage(persents);
                }
            }

            public void onFinish() {
                mTimer.setText("00:00");
                mTimerClock.setPercentage(0);
                final Dialog dialog = new Dialog(getActivity(), "Are you ready?", "\n\n");
                dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timer();
                    }
                });
                dialog.show();
            }
        }.start();

        new LoadAds(view, R.id.adViewSecondTest);
        return view;
    }

    private void timer() {
        final int timeDelta = 220000 + 9200;
         new CountDownTimerPausable(timeDelta, 1000) {
            int oneMinute = 60000;
            int oneMinuteForPercentCounter = 60000;
            int twoMinute = timeDelta - 60000;
            boolean isOneMinuteEnd = false;

            public void onTick(long millisUntilFinished) {
                if (!isFinish) {
                    //Preparation 1 minute
                    if (timeDelta - 1000 < millisUntilFinished) {
                        setBtnRecordingOff();
                    }

                    //Speaking now Answer 13 seconds
                    if (timeDelta - 61000 < millisUntilFinished && millisUntilFinished < timeDelta - 60000) {
                        long_turn();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "part2-longturn-start.mp4");
                        listOfAudio.add(mQuestionsPath + "part2-longturn-start.mp4");
                    }

                    // Candidate long turn 120 seconds
                    if (timeDelta - 61000 - 14000 < millisUntilFinished && millisUntilFinished < timeDelta - 61000 - 13000) {
                        recordStart(mTestFolderPath + "part2-answ1.mp4");
                        setBtnRecordingOn();
                        listOfAudio.add(mTestFolderPath + "part2-answ1.mp4");
                    }
                    //Thanks you  5 seconds
                    if (timeDelta - 61000 - 14000 - 121000 < millisUntilFinished && millisUntilFinished < timeDelta - 61000 - 14000 - 120000) {
                        questions();
                        recordStop();
                        setBtnRecordingOn();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "part2-thanks-after-fuqs.mp4");
                        listOfAudio.add(mQuestionsPath + "part2-thanks-after-fuqs.mp4");
                    }
                    //Question 1  3 seconds
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 < millisUntilFinished && millisUntilFinished <
                            timeDelta - 61000 - 14000 - 121000 - 5000) {
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + longTurn[1]);
                        listOfAudio.add(mQuestionsPath + longTurn[1]);
                    }
                    //Answer 2 5 seconds
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 - 4000
                            < millisUntilFinished && millisUntilFinished < timeDelta - 61000 - 14000 - 121000 - 6000 - 3000) {
                        recordStart(mTestFolderPath + "part2-answ2.mp4");
                        setBtnRecordingOn();
                        listOfAudio.add(mTestFolderPath + "part2-answ2.mp4");
                    }
                    //Question 2   3 seconds
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 < millisUntilFinished && millisUntilFinished <
                            timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 5000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + longTurn[2]);
                        listOfAudio.add(mQuestionsPath + longTurn[2]);
                    }
                    //Answer 3 5 seconds
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 < millisUntilFinished && millisUntilFinished <
                            timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 3000) {
                        recordStart(mTestFolderPath + "part2-answ3.mp4");
                        setBtnRecordingOn();
                        listOfAudio.add(mTestFolderPath + "part2-answ3.mp4");
                    }
                    //End
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 6000 < millisUntilFinished && millisUntilFinished <
                            timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 5000) {
                        recordStop();
                        setBtnRecordingOff();
                        playQuestion(mQuestionsPath + "part2-thanks-after-fuqs.mp4");
                        listOfAudio.add(mQuestionsPath + "part2-thanks-after-fuqs.mp4");

                    }

                    //End
                    if (timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 6000 - 6000 < millisUntilFinished && millisUntilFinished <
                            timeDelta - 61000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 6000 - 5000) {
                        //                    recordStop();
                        setBtnRecordingOff();
                        //                    playQuestion(mQuestionsPath + "intro-frame-s1.mp4");
                        new MergeTask(getActivity()).execute(listOfAudio);
                    }
                }
                if (oneMinute == 0)
                    isOneMinuteEnd = true;

                if (oneMinute != 0 ) {
                    oneMinuteForPercentCounter -= 1000;
                    String v = String.format("%02d", oneMinute / 60000);
                    int va = (int) ((oneMinute % 60000) / 1000);
                    mTimer.setText(v + ":" + String.format("%02d", va));
                    long persents = (60000 - oneMinuteForPercentCounter)/600;
                    Log.d("taras","progress :"+persents);
                    mTimerClock.setPercentage(persents);
                    oneMinute -= 1000;
                }
                if (twoMinute != 0 && isOneMinuteEnd){
                    String v = String.format("%02d", twoMinute / 60000);
                    int va = (int) ((twoMinute % 60000) / 1000);
                    mTimer.setText(v + ":" + String.format("%02d", va));
                    long persents = (169200 - millisUntilFinished)/1600;
                    Log.d("taras","progress :"+persents);
                    mTimerClock.setPercentage(persents);
                    twoMinute -= 1000;
                }
            }

            public void onFinish() {
                mTimer.setText("00:00");

            }
        }.start();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFloat2:
                isFinish = true;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new IntroThirdTestFragment());
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


    private void preparation() {
        ((MainActivity) this.getActivity()).setPageTitle("Part 2 - preparation");
        mTopic.setText(mTopicText);
        mBigText.setText(mBigTextFrame);
    }

    private void long_turn() {
        ((MainActivity) this.getActivity()).setPageTitle("Part 2 - long turn");
        mTopic.setText(mTopicText);
        mBigText.setText(mBigTextFrame);
    }

    private void questions() {
        ((MainActivity) this.getActivity()).setPageTitle("Part 2 - Follow up");
        mBigText.setText(
                "• Now answer both of the follow up questions\n" +
                "• Simple, short answers are fine");
//        mBigText.setText(mBigTextFrame);
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
                + File.separator + "merge_T2_" + MainActivity.sTestFileDate
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
            fragmentTransaction.replace(R.id.container, new IntroThirdTestFragment());
            fragmentTransaction.commit();
            interstitialAds.show();
        }
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

        Log.d("taras","topic :"+Topic);
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
    private String[] setRandomFrame() {
        String[] result = null;
        Random random = new Random();
        int rand = random.nextInt(8) + 1;

        Log.d("taras", "rand :" + rand);
        switch (rand) {
            case 1:
                MainActivity.sTextTask = 605;
                result = Constants.part2Task605;
                mTopicText = "Describe  something you bought that you weren`t satisfied with";
                mTopicName = "Something you bought";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "what you bought\n" +
                                "where you bought it\n" +
                                "why you bought it\n" +
                                "and explain why you weren't satisfied with what you bought";
                break;
            case 2:
                MainActivity.sTextTask = 606;
                result = Constants.part2Task606;
                mTopicText = "Describe a decision someone you know made that you think was wrong";
                mTopicName = "A decision";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "who made the decision and what it was\n" +
                                "why the person made the decision\n" +
                                "what happened as a result of the decision\n" +
                                "and explain why you think the decision was wrong.";
                break;
            case 3:
                MainActivity.sTextTask = 607;
                result = Constants.part2Task607;
                mTopicText = "Describe something you plan to do in your life, NOT related to your work or studies";
                mTopicName = "Something you plan to do";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "what you plan to do\n" +
                                "why you plan to do this\n" +
                                "what you need to do first\n" +
                                "and explain how you will feel if you succeed with your plan";
                break;
            case 4:
                MainActivity.sTextTask = 608;
                result = Constants.part2Task608;
                mTopicText = "Describe a person you enjoy talking to";
                mTopicName = "A person who you enjoy talking to";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "who the person is\n" +
                                "how often you talk to the person\n" +
                                "what you talk about\n" +
                                "and explain why you enjoy talking to this person";

                break;
            case 5:
                MainActivity.sTextTask = 609;
                result = Constants.part2Task609;
                mTopicText = "Describe a film you enjoyed that was about a real person or real event";
                mTopicName = "A film about a real person/event";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "when you saw the film\n" +
                                "who or what the film was about\n" +
                                "what you learned about the person or the event\n" +
                                "and explain why you enjoyed watching this film";
                break;
            case 6:
                MainActivity.sTextTask = 610;
                result = Constants.part2Task610;
                mTopicText = "Describe a vehicle (e.g. a car or a bicycle) you would like to have";
                mTopicName = "A vehicle you would like to have";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "what vehicle you would like to have\n" +
                                "why you would like to have this vehicle\n" +
                                "how you would get this vehicle\n" +
                                "and explain whether you think you will ever have this vehicle";
                break;
            case 7:
                MainActivity.sTextTask = 611;
                result = Constants.part2Task611;
                mTopicText = "Describe something useful you learned from a member of your family";
                mTopicName = "Something useful you learned";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "what you learned\n" +
                                "who in your family you learned it from\n" +
                                "how you learned it\n" +
                                "and explain why learning this has been useful to you";
                break;
            case 8:
                MainActivity.sTextTask = 612;
                result = Constants.part2Task612;
                mTopicText = "Describe a gift or present you gave someone recently";
                mTopicName = "A gift you gave someone recently";
                mBigTextFrame =
                        "You should say:\n\n" +
                                "what the gift was\n" +
                                "who you gave the gift to\n" +
                                "what he/she thought of the gift\n" +
                                "and explain why you chose this gift";
                break;
            default:
                MainActivity.sTextTask = 612;
                result = Constants.part2Task612;
                mTopicText = "Describe a gift or present you gave someone recently";
                mTopicName = "A gift you gave someone recently";
                mBigTextFrame =
                        "You should say:\n\n" +
                        "what the gift was\n" +
                        "who you gave the gift to\n" +
                        "what he/she thought of the gift\n" +
                        "and explain why you chose this gift";
                break;
        }
        return result;
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


//new CountDownTimer(timeToTest, 1000) {
//        int timeDelta = timeToTest;
//
//public void onTick(long millisUntilFinished) {
//        //Intro 1   14 seconds
//        if (timeToTest - 1000 < millisUntilFinished) {
//        Log.d("Jack", String.valueOf(timeDelta));
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + "part2-intro.mp4");
//        listOfAudio.add(mQuestionsPath + "part2-intro.mp4");
//        }
//        //Intro 2   8 seconds
//        if (timeToTest - 16000 < millisUntilFinished && millisUntilFinished < timeToTest - 15000) {
//        timeDelta -= 16000;
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + "part2-intro-2.mp4");
//        listOfAudio.add(mQuestionsPath + "part2-intro-2.mp4");
//        Log.d("Jack", String.valueOf(timeDelta));
//        }
//        //Intro Unical   8 seconds
//        if (timeToTest - 16000 - 9000 < millisUntilFinished && millisUntilFinished <  timeToTest - 16000 - 8000) {
//        timeDelta -= 16000;
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + longTurn[0]);
//        listOfAudio.add(mQuestionsPath + longTurn[0]);
//        Log.d("Jack", String.valueOf(timeDelta));
//        }
//
//        //Preparation 1 minute
//        if (timeToTest - 16000 - 9000 - 9000  < millisUntilFinished && millisUntilFinished <  timeToTest - 16000 - 9000 - 8000) {
//        setBtnRecordingOff();
//
//        }
//
//
//        //Speaking now Answer 13 seconds
//        if (timeToTest - 16000 - 9000 < millisUntilFinished && millisUntilFinished < timeToTest - 16000 - 8000) {
//        timeDelta -= 9000;
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + "part2-longturn-start.mp4");
//        listOfAudio.add(mQuestionsPath + "part2-longturn-start.mp4");
//        }
//
//        // Candidate long turn 120 seconds
//        if (timeToTest - 16000 - 9000 - 14000 < millisUntilFinished && millisUntilFinished  < timeToTest - 16000 - 9000 - 13000) {
//        timeDelta -= 13000;
//        recordStart(mTestFolderPath + "part2-answ1.mp4");
//        setBtnRecordingOn();
//        listOfAudio.add(mTestFolderPath + "part2-answ1.mp4");
//        }
//        //Thanks you  5 seconds
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 < millisUntilFinished && millisUntilFinished < timeToTest - 16000 - 9000 - 14000 - 120000) {
//        timeDelta -= 121000;
//        recordStop();
//        setBtnRecordingOn();
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + "part2-thanks-after-fuqs.mp4");
//        listOfAudio.add(mQuestionsPath + "part2-thanks-after-fuqs.mp4");
//        }
//        //Question 1  3 seconds
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 < millisUntilFinished && millisUntilFinished <
//        timeToTest - 16000 - 9000 - 14000 - 121000 - 5000) {
//        timeDelta -= 6000;
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + longTurn[1]);
//        listOfAudio.add(mQuestionsPath + longTurn[1]);
//        }
//        //Answer 2 5 seconds
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 <
//        millisUntilFinished && millisUntilFinished < timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 3000) {
//        timeDelta -= 4000;
//        recordStart(mTestFolderPath + "part2-answ2.mp4");
//        setBtnRecordingOn();
//        listOfAudio.add(mTestFolderPath+ "part2-answ2.mp4");
//        }
//        //Question 2   3 seconds
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 6000 < millisUntilFinished && millisUntilFinished <
//        timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 5000) {
//        timeDelta -= 6000;
//        recordStop();
//        setBtnRecordingOff();
//        playQuestion(mQuestionsPath + longTurn[2]);
//        listOfAudio.add(mQuestionsPath + longTurn[2]);
//        }
//        //Answer 3 5 seconds
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 < millisUntilFinished && millisUntilFinished <
//        timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 6000 - 3000) {
//        timeDelta -= 4000;
//        recordStart(mTestFolderPath + "part2-answ3.mp4");
//        setBtnRecordingOn();
//        listOfAudio.add(mTestFolderPath + "part2-answ3.mp4");
//        }
//
//        //End
//        if (timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 6000 < millisUntilFinished && millisUntilFinished <
//        timeToTest - 16000 - 9000 - 14000 - 121000 - 6000 - 4000 - 6000 - 4000 - 5000) {
//        recordStop();
//        setBtnRecordingOff();
////                    playQuestion(mQuestionsPath + "intro-frame-s1.mp4");
//        new MergeTask(getActivity()).execute(listOfAudio);
//        }
//        String v = String.format("%02d", millisUntilFinished / 60000);
//        int va = (int) ((millisUntilFinished % 60000) / 1000);
//        mTimer.setText(v + ":" + String.format("%02d", va));
//        }
//
//public void onFinish() {
//        mTimer.setText("00:00");
//        }
//        }.start();