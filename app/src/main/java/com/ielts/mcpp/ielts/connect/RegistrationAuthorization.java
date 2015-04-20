package com.ielts.mcpp.ielts.connect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.common.io.ByteStreams;
import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.model.ParseKeys;
import com.ielts.mcpp.ielts.model.RegistrationForm;
import com.ielts.mcpp.ielts.registration.WelcomeActivity;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Jack on 4/17/2015.
 */
public class RegistrationAuthorization {
    ProgressDialog progressDialog;

    public void regisrate(RegistrationForm registrationForm, Context myContext) {
        final Context context = myContext;
        ParseUser parseUser = ParseKeys.parseUser;
        parseUser.put(ParseKeys.firstName, registrationForm.getFirstName());
        parseUser.put("username", registrationForm.getEmail());
        parseUser.put("password", registrationForm.getPassword());
        parseUser.put(ParseKeys.lastName, registrationForm.getLastName());
        parseUser.put(ParseKeys.email, registrationForm.getEmail());
        parseUser.put("birthday", registrationForm.getDateOfBirth());
        parseUser.put("couponNumber", registrationForm.getPromoCode());
        parseUser.put("nationality", registrationForm.getNationality());
        parseUser.put("whatsYourJob", registrationForm.getProffesion());
        progressDialog = ProgressDialog.show(context, "Sign Up", "Please wait", true);
        parseUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    Toast.makeText(context,
                            "Successfully Signed up, please log in.",
                            Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context,
                            "Sign up Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressDialog.cancel();
            }
        });
    }

    public void logIn(String password, String username, final Context myContext) {
        progressDialog = ProgressDialog.show(myContext, "Log In", "Please wait");
        ParseUser.logInInBackground(username, password,
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(myContext, MainActivity.class);
                            myContext.startActivity(intent);
                            Toast.makeText(myContext,
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(
                                    myContext, "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void restorePasswrod(String email, final Context myContext){
        ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(myContext, "Successfully restored, check your mail",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(
                            myContext, "No such user exist", Toast.LENGTH_LONG).show();
                    Log.d("Jack", "!!!!!! " + e.getMessage());
                }
            }
        });
    }
}
