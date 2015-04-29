package com.ielts.mcpp.ielts.connect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ielts.mcpp.ielts.MainActivity;
import com.ielts.mcpp.ielts.dao.SecurityDAO;
import com.ielts.mcpp.ielts.dao.SecurityDaoImpl;
import com.ielts.mcpp.ielts.model.ParseKeys;
import com.ielts.mcpp.ielts.model.RegistrationForm;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import java.util.List;

/**
 * Created by Jack on 4/17/2015.
 */
public class RegistrationAuthorization {
    ProgressDialog progressDialog;

    public void regisrate(final RegistrationForm registrationForm, final Context myContext,
                          final Activity activity, boolean yesCheckBox) {
        final Context context = myContext;
        ParseUser parseUser = ParseKeys.parseUser;
        parseUser.put(ParseKeys.firstName, registrationForm.getFirstName());
        parseUser.put("username", registrationForm.getEmail().toLowerCase());
        parseUser.put("password", registrationForm.getPassword());
        parseUser.put(ParseKeys.lastName, registrationForm.getLastName());
        parseUser.put(ParseKeys.email, registrationForm.getEmail());
        parseUser.put("birthday", registrationForm.getDateOfBirth());
        parseUser.put("couponNumber", registrationForm.getPromoCode());
        parseUser.put("nationality", registrationForm.getNationality());
        parseUser.put("whatsYourJob", registrationForm.getProffesion());
        parseUser.put("scoreYouWant", registrationForm.getScoreDoYouNeed());
        if (yesCheckBox) {
            parseUser.put("numOfTestTaken",registrationForm.getHowManyTries());
            parseUser.put("takenTestBefore", registrationForm.isTakenTestBefore());
            parseUser.put("whatWasYourScore", registrationForm.getLastScore());
        }
        if (!yesCheckBox){
            parseUser.put("yourLevel", registrationForm.getEnglishLevel());
        }
        progressDialog = ProgressDialog.show(context, "Sign Up", "Please wait", true);
        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    SecurityDAO securityDAO = new SecurityDaoImpl(context);
                    securityDAO.saveUsername(registrationForm.getEmail());
                    securityDAO.savePassword(registrationForm.getPassword());
                    Toast.makeText(context, "Successfully Signed up, please log in.", Toast.LENGTH_LONG).show();

                    context.startActivity(new Intent(context, MainActivity.class));
                    activity.finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context,
                            "Sign up Error " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void logIn(final String password, final String username, final Context myContext, final Activity activity) {
        progressDialog = ProgressDialog.show(myContext, "Log In", "Please wait");
        ParseUser.logInInBackground(username, password,
                new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            progressDialog.dismiss();
                            SecurityDAO securityDAO = new SecurityDaoImpl(myContext);
                            securityDAO.saveUsername(username);
                            securityDAO.savePassword(password);
                            Intent intent = new Intent(myContext, MainActivity.class);
                            myContext.startActivity(intent);
                            Toast.makeText(myContext, "Successfully Logged in", Toast.LENGTH_LONG).show();
                            activity.finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(myContext, "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void restorePasswrod(String email, final Context myContext){
        ParseUser.requestPasswordResetInBackground(email.toLowerCase(), new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(myContext, "Successfully restored, check your mail",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(
                            myContext, "No such user exist", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getSecuredKey(){
        String securedKey = null;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("objectId","U8mCwTHOaC");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    objects.get(1).getString("securedKey");
                } else {
                    // error
                }
            }
        });
        return securedKey;
    }
}
