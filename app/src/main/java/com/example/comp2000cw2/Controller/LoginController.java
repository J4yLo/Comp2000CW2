package com.example.comp2000cw2.Controller;

import com.example.comp2000cw2.Model.User;
import com.example.comp2000cw2.View.ILoginView;

public class LoginController implements ILoginContext{

    ILoginView LV;

    public LoginController(ILoginView LV){
        this.LV = LV;
    }



    @Override
    public void OnLogin(String UserID, String Password, String Firstname) {
        User usr = new User(UserID,Password, Firstname, "lad");
        int logincode = usr.Validator();

        if (logincode == 0)
        {
            LV.LoginError("One or more fields are empty");
        }
        else if(logincode == 1)
        {
            LV.LoginError("Username needs to be an integer value");
        }
        else if(logincode == -1)
        {
            LV.LoginSuccess("Successful Login");
        }
    }
}
