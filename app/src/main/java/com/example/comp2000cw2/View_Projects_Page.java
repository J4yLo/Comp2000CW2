package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.AdapterView;

import com.example.comp2000cw2.Projects_ListAdapter;
import com.example.comp2000cw2.R;
import com.example.comp2000cw2.databinding.ActivityMainBinding;
import com.example.comp2000cw2.databinding.ActivityViewProjectsPageBinding;
import com.example.comp2000cw2.databinding.ProjectsLayoutBinding;

import java.util.ArrayList;

public class View_Projects_Page extends AppCompatActivity {

    ActivityViewProjectsPageBinding binding;

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
        binding = ActivityViewProjectsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Values to be implemented into list view
        int[] imgID = {R.drawable.ic_baseline_assessment_24, R.drawable.ic_baseline_cloud_upload_24, R.drawable.ic_baseline_add_box_24};
        String[] projName = {"API Project", "AI Project", "Upscaling Algorithm"};
        String[] projUsers = {"1,2,3", "1,5,3", "1"};
        String[] projDesc = {"Some long details about the current state of the project", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "no desc"};
        String[] projCode = {"1","2","3"};
        String[] projDate = {"11/07/2021", "11/11/2021", "01/01/2022"};


        ArrayList<Projects> projectsArrayList = new ArrayList<>();
        for (int i = 0; i < imgID.length; i ++)
        {
            Projects proj = new Projects(projName[i],projUsers[i],projDesc[i],projCode[i],projDate[i],imgID[i]);
            projectsArrayList.add(proj);
        }


        ListAdapter listAdapter = new Projects_ListAdapter(this, projectsArrayList);
        binding.ProjectsList.setAdapter(listAdapter);
        binding.ProjectsList.setClickable(true);

        binding.ProjectsList.setOnItemClickListener((parent, view, position, id) -> {

            Intent ProjectDetails = new Intent(this,Home_Page.class);
            //Project Details
            ProjectDetails.putExtra("Name",projName[position]);
            ProjectDetails.putExtra("Users",projUsers[position]);
            ProjectDetails.putExtra("Description",projDesc[position]);
            ProjectDetails.putExtra("Code",projCode[position]);
            ProjectDetails.putExtra("Date",projDate[position]);
            ProjectDetails.putExtra("imgID",imgID[position]);

            //User Details
            ProjectDetails.putExtra("UserID", UsersID);
            ProjectDetails.putExtra("UserName", UsersName);




            startActivity(ProjectDetails);

        });

    }


}