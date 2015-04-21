package com.ielts.mcpp.ielts.registration;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;

/**
 * Created by Jack on 4/15/2015.
 */
public class SignUpSecondFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_2nd_screen, container, false);
        ButtonRectangle nextButton = (ButtonRectangle) view.findViewById(R.id.button_screen_2_next);
        nextButton.setOnClickListener(nextButtonListener);
        EditText prof = (EditText) view.findViewById(R.id.edit_text_work);
        WelcomeActivity.registrationForm.setProffesion(prof.getText().toString());
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.sing_up_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonWorking:
                        WelcomeActivity.registrationForm.setWorkOrStudy("working");
                        break;
                    case R.id.radioButtonStudent:
                        WelcomeActivity.registrationForm.setWorkOrStudy("student");
                        break;
                    case R.id.radioButtonBoth:
                        WelcomeActivity.registrationForm.setWorkOrStudy("both");
                        break;
                    case R.id.radioButtonNeither:
                        WelcomeActivity.registrationForm.setWorkOrStudy("neither");
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new RegistrationAuthorization().regisrate(WelcomeActivity.registrationForm, getActivity(), getActivity());
        }
    };
}
