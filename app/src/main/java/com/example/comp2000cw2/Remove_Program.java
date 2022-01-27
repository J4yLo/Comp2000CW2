package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.comp2000cw2.R;

public class Remove_Program extends AppCompatActivity {

    private String UsersID;
    private String UsersName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        UsersID = intent.getStringExtra("UserID");
        UsersName = intent.getStringExtra("UserName");


        // Full Screen Window
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Render Page Content
        setContentView(R.layout.activity_remove_program);

        //Variables
        ImageButton Back = findViewById(R.id.BackBtn);

        //Back Button Function
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
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

}
