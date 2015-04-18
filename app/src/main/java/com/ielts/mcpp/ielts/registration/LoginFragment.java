package com.ielts.mcpp.ielts.registration;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Jack on 4/15/2015.
 */
public class LoginFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButtonRectangle btnLogin = (ButtonRectangle) view.findViewById(R.id.button_login);
        ButtonRectangle btnSignUp = (ButtonRectangle) view.findViewById(R.id.button_sign_up);
        ButtonRectangle btnRestore = (ButtonRectangle) view.findViewById(R.id.button_restore);
        btnLogin.setOnClickListener(loginButtonListener);
        btnSignUp.setOnClickListener(signUpButtonListener);
        btnRestore.setOnClickListener(restoreButtonListener);

        Button testLl = (Button) view.findViewById(R.id.test_log_in);
        testLl.setOnClickListener(testLogInListener);
        return view;
    }

    View.OnClickListener loginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().getIntent().setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            TextView password = (TextView) view.findViewById(R.id.login_screen_password);
            TextView username = (TextView) view.findViewById(R.id.login_screen_username);
            new RegistrationAuthorization().logIn(password.getText().toString(),
                    username.getText().toString(), getActivity());
        }};

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
        }
    };
}
