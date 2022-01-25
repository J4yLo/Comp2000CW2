package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView UserID = findViewById(R.id.UserID);
        TextView Password = findViewById(R.id.Password);


        Button Login = (Button) findViewById(R.id.LoginBtn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserID.getText().toString() == null && Password.getText().toString() == null)
                {
                    Toast.makeText(MainActivity.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        if (UserID.getText().toString().equals("admin") && Password.getText().toString().equals("admin"))
                        {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Username Or Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                }

            }
        });


    }




}