package com.ielts.mcpp.ielts.registration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;


/**
 * Created by Jack on 4/15/2015.
 */
public class LoginFragment extends Fragment {
    View view;

    private EditText usernameEditText;
    private EditText passwordEditText;
    AwesomeValidation mAwesomeValidation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        usernameEditText = (EditText) view.findViewById(R.id.login_screen_username);
        passwordEditText = (EditText) view.findViewById(R.id.login_screen_password);
        ButtonRectangle btnLogin = (ButtonRectangle) view.findViewById(R.id.button_login);
        ButtonRectangle btnSignUp = (ButtonRectangle) view.findViewById(R.id.button_sign_up);
        ButtonRectangle btnRestore = (ButtonRectangle) view.findViewById(R.id.button_restore);
        btnLogin.setRippleSpeed(40F);
        btnSignUp.setRippleSpeed(40F);
        btnRestore.setRippleSpeed(40F);
        btnLogin.setOnClickListener(loginButtonListener);
        btnSignUp.setOnClickListener(signUpButtonListener);
        btnRestore.setOnClickListener(restoreButtonListener);

        mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        mAwesomeValidation.addValidation(usernameEditText, getResources().getString(R.string.email_regex),
                getResources().getString(R.string.error_wrong_email));
        mAwesomeValidation.addValidation(passwordEditText, "^[a-zA-Z0-9]*.{6,20}",
                getResources().getString(R.string.error_wrong_password));
        Button testLl = (Button) view.findViewById(R.id.test_log_in);
        testLl.setOnClickListener(testLogInListener);

        return view;
    }

    View.OnClickListener loginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mAwesomeValidation.validate()) {
                getActivity().getIntent().setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                TextView username = (TextView) view.findViewById(R.id.login_screen_username);
                TextView password = (TextView) view.findViewById(R.id.login_screen_password);
                new RegistrationAuthorization().logIn(password.getText().toString(),
                        username.getText().toString().toLowerCase(), getActivity(), getActivity());
            }
        }
    };

    View.OnClickListener signUpButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.replace(R.id.welcome_activity_container, new SignUpFirstFragment());
            fragmentTransaction.commit();
        }
    };

    View.OnClickListener restoreButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.welcome_activity_container, new RestorePasswordFragment());
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }
    };
    View.OnClickListener testLogInListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
//            String sss = "sdcard/Download/12.mp3";
//            new AudioSend().sendAudio(getActivity(), sss,sss,"sdcard/Download/11.mp4");
        }
    };
    View.OnFocusChangeListener emailValidateListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                mAwesomeValidation.validate();

                Log.d("Jack", "!!!!! No focus");
            }
        }
    };
}
