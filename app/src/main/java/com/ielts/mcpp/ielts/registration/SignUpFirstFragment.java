package com.ielts.mcpp.ielts.registration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.R;

import java.util.Calendar;

/**
 * Created by Jack on 4/15/2015.
 */
public class SignUpFirstFragment extends Fragment {
    View view;
    private EditText usernameEditText;
    private EditText passwordEditText;
    AwesomeValidation mAwesomeValidation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_1st_screen, container, false);
        ButtonRectangle btnHome = (ButtonRectangle) view.findViewById(R.id.button_screen_1_home);
        ButtonRectangle btnNext = (ButtonRectangle) view.findViewById(R.id.button_screen_1_next);
        usernameEditText = (EditText) view.findViewById(R.id.edit_email);
        passwordEditText = (EditText) view.findViewById(R.id.edit_password);
        btnHome.setOnClickListener(homeButtonListener);
        btnNext.setOnClickListener(nextButtonListener);
        final EditText dateOfBirth = (EditText) view.findViewById(R.id.edit_date_of_birth);
        dateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == dateOfBirth && event.getAction() == MotionEvent.ACTION_DOWN)
                    new DatePickerFragment().show(getFragmentManager(), "datePicker");
                return false;
            }
        });
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        mAwesomeValidation.addValidation(usernameEditText, getResources().getString(R.string.email_regex),
                getResources().getString(R.string.error_wrong_email));
        mAwesomeValidation.addValidation(passwordEditText, "^[a-zA-Z0-9]*.{6,20}",
                getResources().getString(R.string.error_wrong_password));

        return view;
    }
    View.OnClickListener homeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getFragmentManager().popBackStack();
        }
    };
    View.OnClickListener nextButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAwesomeValidation.validate()) {
                EditText firstName = (EditText) view.findViewById(R.id.edit_first_name);
                EditText lastName = (EditText) view.findViewById(R.id.edit_last_name);
                EditText password = (EditText) view.findViewById(R.id.edit_password);
                EditText nationality = (EditText) view.findViewById(R.id.edit_nationality);
                EditText dateOfBirth = (EditText) view.findViewById(R.id.edit_date_of_birth);
                EditText email = (EditText) view.findViewById(R.id.edit_email);
                EditText promo = (EditText) view.findViewById(R.id.edit_promo_code);
                WelcomeActivity.registrationForm.setFirstName(firstName.getText().toString());
                WelcomeActivity.registrationForm.setLastName(lastName.getText().toString());
                WelcomeActivity.registrationForm.setPassword(password.getText().toString());
                WelcomeActivity.registrationForm.setNationality(nationality.getText().toString());
                WelcomeActivity.registrationForm.setDateOfBirth(dateOfBirth.getText().toString());
                WelcomeActivity.registrationForm.setEmail(email.getText().toString());
                WelcomeActivity.registrationForm.setPromoCode(promo.getText().toString());
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.welcome_activity_container, new SignUpSecondFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        }
    };



    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            EditText dateText = (EditText) getActivity().findViewById(R.id.edit_date_of_birth);
            dateText.setText(day + "/" + month + "/" + year);
        }
    }
}
