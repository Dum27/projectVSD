package com.ielts.mcpp.ielts.registration;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;

public class SignUpThirdFragment extends Fragment {
    View view;
    CheckBox yesCheckBox;
    CheckBox noCheckBox;

    public SignUpThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_3nd_screen, container, false);
        ButtonRectangle btnNext = (ButtonRectangle) view.findViewById(R.id.button_screen_3_next);
        yesCheckBox = (CheckBox) view.findViewById(R.id.checkBox_yes);
        noCheckBox  = (CheckBox) view.findViewById(R.id.checkBox_no);
        yesCheckBox.setOnClickListener(checkBoxListener);
        noCheckBox.setOnClickListener(checkBoxListener);
        btnNext.setOnClickListener(nextButtonListener);
        return view;
    }

    View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (yesCheckBox.isChecked()) {
                EditText howManyTries = (EditText) view.findViewById(R.id.edit_yes_number_of_try);
                EditText scoreDoYouNeed = (EditText) view.findViewById(R.id.edit_yes_score_need);
                EditText lastScore = (EditText) view.findViewById(R.id.edit_yes_previous_score);
                EditText language = (EditText) view.findViewById(R.id.edit_yes_additional_screen_language);
                WelcomeActivity.registrationForm.setHowManyTries(howManyTries.getText().toString());
                WelcomeActivity.registrationForm.setLastScore(lastScore.getText().toString());
                WelcomeActivity.registrationForm.setScoreDoYouNeed(scoreDoYouNeed.getText().toString());
                WelcomeActivity.registrationForm.setAdditionalScreenLanguage(language.getText().toString());
                WelcomeActivity.registrationForm.setTakenTestBefore(true);
            } else {
                EditText scoreDoYouNeed2 = (EditText) view.findViewById(R.id.edit_no_score_need);
                EditText englishLevel = (EditText) view.findViewById(R.id.edit_no_english_level);
                EditText language2 = (EditText) view.findViewById(R.id.edit_no_additional_screen_language);
                WelcomeActivity.registrationForm.setScoreDoYouNeed(scoreDoYouNeed2.getText().toString());
                WelcomeActivity.registrationForm.setEnglishLevel(englishLevel.getText().toString());
                WelcomeActivity.registrationForm.setAdditionalScreenLanguage(language2.getText().toString());
            }
            new RegistrationAuthorization().regisrate(WelcomeActivity.registrationForm, getActivity(), getActivity());
        }
    };

    View.OnClickListener checkBoxListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.checkBox_yes:
                    noCheckBox.setChecked(false);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.registration_yes_no_container, new ThirdYesCheckFragment());
                    fragmentTransaction.commit();
                    break;
                case R.id.checkBox_no:
                    yesCheckBox.setChecked(false);
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.registration_yes_no_container, new ThirdNoCheckFragment());
                    fragmentTransaction1.commit();
                    break;
                default:
                    break;
            }
        }
    };
}
