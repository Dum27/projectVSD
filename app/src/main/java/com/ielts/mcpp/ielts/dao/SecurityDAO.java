package com.ielts.mcpp.ielts.dao;

import java.io.ObjectInput;

/**
 * Created by Jack on 4/19/2015.
 */
public interface SecurityDAO {

    public void saveUsername(String username);
    public void savePassword(String password);
    public String getUsername();
    public String getPassword();

}
