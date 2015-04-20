package com.ielts.mcpp.ielts.registration;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.model.RegistrationForm;

public class WelcomeActivity extends ActionBarActivity {

    public static RegistrationForm registrationForm;
    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        registrationForm = new RegistrationForm();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.welcome_activity_container, new LoginFragment());
        fragmentTransaction.commit();
    }
}
