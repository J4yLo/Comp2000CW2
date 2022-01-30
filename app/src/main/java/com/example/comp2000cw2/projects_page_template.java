package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;

public class projects_page_template extends AppCompatActivity {

    private String UsersID;
    private String UsersName;

    private String ProjectUsersID;
    private String ProjectUsersName;
    private String ProjectName;
    private String Description;
    private String Code;
    private String projDate;
    private String ImgID;
    private String photo;
    private String Thumbnail;
    private String Posterimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        UsersID = intent.getStringExtra("UserID");
        UsersName = intent.getStringExtra("UserName");



        //Project Variables
        ProjectUsersID = intent.getStringExtra("ProjStudentID");
        ProjectName = intent.getStringExtra("Name");
        ProjectUsersName = intent.getStringExtra("Users");
        Description = intent.getStringExtra("Description");
        Code = intent.getStringExtra("Code");
        projDate = intent.getStringExtra("Date");

        //Images
        ImgID = intent.getStringExtra("imgID");
        photo = intent.getStringExtra("Photo");
        Thumbnail = intent.getStringExtra("Thumbnail");
        Posterimg = intent.getStringExtra("Poster");




        // Full Screen Window
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Render Page
        setContentView(R.layout.activity_projects_page_template);



        //UI Variables
        TextView ProjectTitle = findViewById(R.id.ProjectsTitle);
        TextView Username = findViewById(R.id.Username);
        TextView Date = findViewById(R.id.Date);
        TextView ProjCode = findViewById(R.id.ProjCode);
        TextView ProjUserID = findViewById(R.id.ProjUserID);
        TextView projDescription = findViewById(R.id.Description);

        //Image Variables
        ImageView Poster = findViewById(R.id.Poster);
        ImageView Photo = findViewById(R.id.Photo);
        ImageView ProjThumbnail = findViewById(R.id.ProjThumbnail);


        //Set UI variables
        ProjectTitle.setText(ProjectName);
        Username.setText(ProjectUsersName);
        Date.setText(projDate);
        ProjCode.setText(Code);
        projDescription.setText(Description);
        ProjUserID.setText(ProjectUsersID);



        // Get photo
        byte[] bytes =Base64.decode(photo,Base64.DEFAULT);
        if (bytes != null){
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            Photo.setImageBitmap(bitmap);
        }
        else
            {
                int image = getResources().getIdentifier("@drawable/ic_baseline_image_24", null, getPackageName());
                Drawable Defultimg = getResources().getDrawable(image);
                Photo.setImageDrawable(Defultimg);
        }

        //Get Thumbnail
        try {

            int DefultThumbnail = getResources().getIdentifier("@drawable/ic_baseline_event_available_24", null, getPackageName());
            Glide.with(projects_page_template.this).load(Thumbnail).placeholder(DefultThumbnail).into(ProjThumbnail);

        } catch (Exception e) {
            int DefultThumbnail = getResources().getIdentifier("@drawable/ic_baseline_event_available_24", null, getPackageName());
            Drawable Defultimg = getResources().getDrawable(DefultThumbnail);
            ProjThumbnail.setImageDrawable(Defultimg);
        }


        //Get poster

        try {
            int DefaultPoster = getResources().getIdentifier("@drawable/ic_baseline_image_24", null, getPackageName());
            Glide.with(projects_page_template.this).load(Posterimg).load(DefaultPoster).into(Poster);

        } catch (Exception e) {
            int DefaultPoster = getResources().getIdentifier("@drawable/ic_baseline_image_24", null, getPackageName());
            Drawable Defultimg = getResources().getDrawable(DefaultPoster);
            Poster.setImageDrawable(Defultimg);
        }





    }
}