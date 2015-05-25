package com.ielts.mcpp.ielts.registration;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ielts.mcpp.ielts.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdYesCheckFragment extends Fragment {
    View view;

    public ThirdYesCheckFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_yes_check, container, false);
        EditText screenLang    = (EditText) view.findViewById(R.id.edit_yes_additional_screen_language);
        EditText previousScore = (EditText) view.findViewById(R.id.edit_yes_previous_score);
        EditText scoreNeed     = (EditText) view.findViewById(R.id.edit_yes_score_need);
        screenLang.   setOnTouchListener(languageListener);
        previousScore.setOnTouchListener(neededScoreListener);
        scoreNeed.    setOnTouchListener(neededScoreListener);
        return view;
    }

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
