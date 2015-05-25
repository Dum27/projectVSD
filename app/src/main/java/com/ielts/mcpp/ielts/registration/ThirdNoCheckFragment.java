package com.ielts.mcpp.ielts.registration;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ielts.mcpp.ielts.R;

import java.util.Calendar;

public class ThirdNoCheckFragment extends Fragment {

    View view;
    EditText needScore;
    EditText englishLevel;
    EditText screenLanguage;

    public ThirdNoCheckFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_no_check, container, false);
        englishLevel= (EditText) view.findViewById(R.id.edit_no_english_level);
        needScore = (EditText) view.findViewById(R.id.edit_no_score_need);
        screenLanguage = (EditText) view.findViewById(R.id.edit_no_additional_screen_language);
        englishLevel.setOnTouchListener(englishLevelListener);
        needScore.setOnTouchListener(neededScoreListener);
        screenLanguage.setOnTouchListener(languageListener);


        return view;
    }

    View.OnTouchListener englishLevelListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                new MaterialDialog.Builder(getActivity())
                        .title("English level")
                        .items(R.array.english_level)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                EditText thisField = (EditText) v;
                                thisField.setText(text);
                            }
                        })
                        .show();
            }
            return false;
        }
    };

    View.OnTouchListener neededScoreListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                new MaterialDialog.Builder(getActivity())
                        .title("What score do you need?")
                        .items(R.array.score_level)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                EditText thisField = (EditText) v;
                                thisField.setText(text);
                            }
                        })
                        .show();
            }
            return false;
        }
    };

    View.OnTouchListener languageListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                new MaterialDialog.Builder(getActivity())
                        .title("What score do you need?")
                        .items(R.array.screen_language)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                EditText thisField = (EditText) v;
                                thisField.setText(text);
                            }
                        })
                        .show();
            }
            return false;
        }
    };

}
