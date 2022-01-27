package com.example.comp2000cw2.Model;

import android.content.Intent;
import android.text.TextUtils;

public class User implements IUser{

    private String Firstname, Lastname,UserID,Password;

    public User(String firstname, String lastname, String userID, String password) {
        Firstname = firstname;
        Lastname = lastname;
        UserID = userID;
        Password = password;
    }


    @Override
    public String getFirstName() {
        return Firstname;
    }

    @Override
    public String getLastName() {
        return Lastname;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public String getUserID() {
        return UserID;
    }











    //Login Functions
    public boolean IDChecker()
        {
        try{
            Integer.parseInt(getFirstName());
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public int Validator() {
        if (TextUtils.isEmpty(getUserID()) || TextUtils.isEmpty(getPassword()) || TextUtils.isEmpty(getFirstName()))
        {
            return 0;
        }
        else if (IDChecker())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
