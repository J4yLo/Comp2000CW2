package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp2000cw2.MainActivity;
import com.example.comp2000cw2.Model.IUser;
import com.example.comp2000cw2.Model.User;
import com.example.comp2000cw2.Remove_Program;
import com.example.comp2000cw2.Update_Project;
import com.example.comp2000cw2.View.ILoginView;
import com.example.comp2000cw2.View_Projects_Page;

public class Home_Page extends AppCompatActivity {

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
        setContentView(R.layout.activity_home_page);

        //UI Variables
        TextView ViewProjects = findViewById(R.id.ViewProjects);
        TextView CreateProjects = findViewById(R.id.CreateProjects);
        TextView UpdateProjects = findViewById(R.id.UpdateProjects);
        TextView DeleteProject = findViewById(R.id.DeleteProject);
        TextView LogOut = findViewById(R.id.LogOut);
        TextView Username = findViewById(R.id.UserName);



        // Get First Name
        String FirstName;
        try {
            int indx = UsersName.lastIndexOf(' ');
            FirstName = UsersName.substring(0, indx);
        }catch(Exception e){
            FirstName = UsersName;
        }





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



        //Dynamic elements of UI
        Username.setText(FirstName);

    }


    //Methods To Open Pages
    public void OpenHomePage(){
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);

    }
    public void OpenCreateProjects(){
        Intent CreateProjects = new Intent(this, Create_A_Programme.class);
        CreateProjects.putExtra("UserID", UsersID);
        CreateProjects.putExtra("UserName", UsersName);
        startActivity(CreateProjects);

    }
    public void OpenUpdateProjects(){
        Intent UpdateProjects = new Intent(this, Update_Project.class);
        UpdateProjects.putExtra("UserID", UsersID);
        UpdateProjects.putExtra("UserName", UsersName);
        startActivity(UpdateProjects);

    }
    public void OpenViewProjects(){
        Intent ViewProjects = new Intent(this, View_Projects_Page.class);
        ViewProjects.putExtra("UserID", UsersID);
        ViewProjects.putExtra("UserName", UsersName);
        startActivity(ViewProjects);

    }
    public void OpenDeleteProject(){
        Intent DeleteProject = new Intent(this, Remove_Program.class);
        DeleteProject.putExtra("UserID", UsersID);
        DeleteProject.putExtra("UserName", UsersName);
        startActivity(DeleteProject);

    }



}