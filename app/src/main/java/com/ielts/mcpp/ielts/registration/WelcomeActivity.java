package com.ielts.mcpp.ielts.registration;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ielts.mcpp.ielts.R;
import com.ielts.mcpp.ielts.connect.RegistrationAuthorization;
import com.ielts.mcpp.ielts.dao.Dao;
import com.ielts.mcpp.ielts.dao.SecurityDAO;
import com.ielts.mcpp.ielts.dao.SecurityDaoImpl;
import com.ielts.mcpp.ielts.model.RegistrationForm;

import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends ActionBarActivity {

    public static RegistrationForm registrationForm;
    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        traverse(new File(Environment.getExternalStorageDirectory() + "/questions"));

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/blue.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        setContentView(R.layout.activity_welcome);
        registrationForm = new RegistrationForm();
        SecurityDAO securityDAO = new SecurityDaoImpl(this);
        if (securityDAO.getPassword() != null)
            new RegistrationAuthorization().logIn(securityDAO.getPassword(), securityDAO.getUsername(),
                    this, this);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.welcome_activity_container, new LoginFragment());
        fragmentTransaction.commit();
        Dao dao = new Dao(this);
        if (dao.checkAudioExist() == null)
            dao.saveAudio();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
        if (getFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
//    public void traverse (File dir) {
//        if (dir.exists()) {
//            File[] files = dir.listFiles();
//            for (int i = 0; i < files.length; ++i) {
//                File file = files[i];
//                if (file.isDirectory()) {
//                    traverse(file);
//                } else {
//                    Log.d("Jack", file.getName());
//                }
//            }
//        }
//    }
}
