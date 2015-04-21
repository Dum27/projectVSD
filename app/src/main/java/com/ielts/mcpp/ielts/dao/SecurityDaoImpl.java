package com.ielts.mcpp.ielts.dao;

import android.content.Context;

import com.ielts.mcpp.ielts.R;

/**
 * Created by Jack on 4/21/2015.
 */
public class SecurityDaoImpl implements SecurityDAO {
    Context context;
    SecurePreferences preferences;
    String prefUsername;
    String prefPassword;

    public SecurityDaoImpl(Context context) {
        this.context = context;
        prefUsername = context.getResources().getString(R.string.username_login);
        prefPassword = context.getResources().getString(R.string.password_login);
        preferences = new SecurePreferences(context, "security-preferences", "74C55D53BCCFAE45582C27AF644FA", true);
    }

    @Override
    public void saveUsername(String username) {
        preferences.put(prefUsername, username);
    }

    @Override
    public void savePassword(String password) {
        preferences.put(prefPassword, password);
    }

    @Override
    public String getUsername() {
        return preferences.getString(prefUsername);
    }

    @Override
    public String getPassword() {
        return preferences.getString(prefPassword);
    }
}
