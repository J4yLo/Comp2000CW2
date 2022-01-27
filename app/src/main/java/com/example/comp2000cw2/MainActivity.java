package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp2000cw2.Controller.ILoginContext;
import com.example.comp2000cw2.Controller.LoginController;
import com.example.comp2000cw2.Model.User;
import com.example.comp2000cw2.View.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {

    ILoginContext LC;
    private String UsersID;
    private String UsersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Full Screen Window
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Render Page Content
        setContentView(R.layout.activity_main);

        LC = new LoginController(this);


        //Variables

        TextView UserName = findViewById(R.id.UserName);
        TextView UserID = findViewById(R.id.UserID);
        TextView Password = findViewById(R.id.Password);
        Button Login = (Button) findViewById(R.id.LoginBtn);

        //Login Function
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass through values to controller

                UsersID = UserID.getText().toString().trim();
                UsersName = UserName.getText().toString().trim();

                LC.OnLogin(UserID.getText().toString().trim(),Password.getText().toString().trim(),UserName.getText().toString().trim());

            }
        });


    }

    // Change Pages
    public void OpenHomePage(){

        Intent HomePage = new Intent(this, Home_Page.class);
        HomePage.putExtra("UserID", UsersID);
        HomePage.putExtra("UserName", UsersName);
        startActivity(HomePage);
    }


    // Login Functions
    @Override
    public void LoginSuccess(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        OpenHomePage();
    }

    @Override
    public void LoginError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}