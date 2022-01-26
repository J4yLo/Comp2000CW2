package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Full Screen Window
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Render Page Content
        setContentView(R.layout.activity_home_page);

        //UI Variables
        TextView ViewProjects = findViewById(R.id.ViewProjects);
        TextView CreateProjects = findViewById(R.id.CreateProjects);
        TextView UpdateProjects = findViewById(R.id.UpdateProjects);
        TextView DeleteProject = findViewById(R.id.DeleteProject);
        TextView LogOut = findViewById(R.id.LogOut);




        // Methods To Change Page
        ViewProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenViewProjects();
            }
        });

        CreateProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCreateProjects();
            }
        });

        UpdateProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenUpdateProjects();
            }
        });

        DeleteProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDeleteProject();
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
            }
        });

    }


    //Methods To Open Pages
    public void OpenHomePage(){
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);

    }
    public void OpenCreateProjects(){
        Intent CreateProjects = new Intent(this, Create_A_Programme.class);
        startActivity(CreateProjects);

    }
    public void OpenUpdateProjects(){
        Intent UpdateProjects = new Intent(this, Update_Project.class);
        startActivity(UpdateProjects);

    }
    public void OpenViewProjects(){
        Intent ViewProjects = new Intent(this, View_Projects_Page.class);
        startActivity(ViewProjects);

    }
    public void OpenDeleteProject(){
        Intent DeleteProject = new Intent(this, Remove_Program.class);
        startActivity(DeleteProject);

    }
}