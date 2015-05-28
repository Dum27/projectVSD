package com.ielts.mcpp.ielts.registration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;

/**
 * Created by Jack on 4/15/2015.
 */
public class SignUpSecondFragment extends Fragment {
    View view;
    LinearLayout layoutAfterRadio;
    TextView afterRadioText;
    EditText afterRadioEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_2nd_screen, container, false);
        ButtonRectangle nextButton = (ButtonRectangle) view.findViewById(R.id.button_screen_2_next);
        nextButton.setOnClickListener(nextButtonListener);
        EditText prof = (EditText) view.findViewById(R.id.edit_text_work);
        layoutAfterRadio = (LinearLayout) view.findViewById(R.id.second_screen_after_radio);
        afterRadioText = (TextView) view.findViewById(R.id.text_second_screen_after_radio_chooser);
        afterRadioEdit = (EditText) view.findViewById(R.id.edit_second_screen_after_radio_chooser);
        WelcomeActivity.registrationForm.setProffesion(prof.getText().toString());
        layoutAfterRadio.setVisibility(View.GONE);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.sing_up_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonWorking:
                        layoutAfterRadio.setVisibility(View.VISIBLE);
                        WelcomeActivity.registrationForm.setWorkOrStudy("working");
                        afterRadioText.setText("What`s your job?");
                        break;
                    case R.id.radioButtonStudent:
                        layoutAfterRadio.setVisibility(View.VISIBLE);
                        WelcomeActivity.registrationForm.setWorkOrStudy("student");
                        afterRadioText.setText("Your major");
                        break;
                    case R.id.radioButtonBoth:
                        layoutAfterRadio.setVisibility(View.GONE);
                        WelcomeActivity.registrationForm.setWorkOrStudy("both");
                        break;
                    case R.id.radioButtonNeither:
                        layoutAfterRadio.setVisibility(View.VISIBLE);
                        WelcomeActivity.registrationForm.setWorkOrStudy("neither");
                        afterRadioText.setText("What do you do?");
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
            WelcomeActivity.registrationForm.setWhatDoYouDo(afterRadioEdit.getText().toString());
//            new RegistrationAuthorization().regisrate(WelcomeActivity.registrationForm, getActivity(), getActivity());
            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.welcome_activity_container, new SignUpThirdFragment());
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }
    };
}
