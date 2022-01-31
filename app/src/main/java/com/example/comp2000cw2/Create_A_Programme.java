package com.example.comp2000cw2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comp2000cw2.Home_Page;
import com.example.comp2000cw2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class Create_A_Programme extends AppCompatActivity {

    private String UsersID;
    private String UsersName;
    private ImageView UsrIMg;
    private TextView ImgText;
    TextView Upload;
    private RequestQueue requestQueue;
    private Uri IMGUri;
    private String Project;
    private String ProjectCO;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        setContentView(R.layout.activity_create_aprogramme);

        //Notifications Channel
        NotificationChannel Nchannel = new NotificationChannel("NotificationChannel", "Notifications", NotificationManager.IMPORTANCE_DEFAULT);
        Nchannel.setDescription("Notifications");
        NotificationManager notifyManager = getSystemService(NotificationManager.class);
        notifyManager.createNotificationChannel(Nchannel);

        //UI Variables
        Upload = findViewById(R.id.Upload);
        ImageButton Back = findViewById(R.id.BackBtn);
        EditText projTitle = findViewById(R.id.ProjectTitle);
        EditText projDesc = findViewById(R.id.ProjectDescription);
        ImgText = findViewById(R.id.AddImage);

        //Get Year of Project
        int year = Calendar.getInstance().get(Calendar.YEAR);


        //Upload Button Function
        Upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";

                int UID = 0;
                String[] Names;
                String FirstName;
                String Lastname;
                try {
                    UID = Integer.parseInt(UsersID);
                    int indx = UsersName.lastIndexOf(' ');
                    FirstName = UsersName.substring(0, indx);
                    Lastname = UsersName.substring(indx + 1);

                }catch(Exception e){
                    FirstName = UsersName;
                    Lastname = "None";

                }

                //Test For User First And Last Name
                //Toast.makeText(Create_A_Programme.this, FirstName, Toast.LENGTH_SHORT).show();
                //Toast.makeText(Create_A_Programme.this, Lastname, Toast.LENGTH_SHORT).show();

                Project = projTitle.getText().toString();
                Map<String, Object> params = new ArrayMap<>();
                params.put("StudentID", UID);
                params.put("Title", projTitle.getText().toString());
                params.put("Description", projDesc.getText().toString());
                params.put("Year", year );
                params.put("First_Name", FirstName);
                params.put("Second_Name", Lastname);


                JSONObject programme = new JSONObject(params);
                JsonObjectRequest  newProgramme = new JsonObjectRequest (Request.Method.POST, url, programme, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(Create_A_Programme.this, "Success Programme Uploaded", Toast.LENGTH_SHORT).show();
                        projectnotify();


                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Create_A_Programme.this, "something Went Wrong Please try again", Toast.LENGTH_SHORT).show();
                    }
                });

                
                requestQueue = Volley.newRequestQueue(Create_A_Programme.this);
                requestQueue.add(newProgramme);
            }
        });

        //Back Button Function
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
            }
        });
    }


    //notification
    public void projectnotify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "NotificationChannel")
                .setSmallIcon(R.drawable.ic_baseline_add_box_24)
                .setContentTitle("Upload Project")
                .setContentText("Successful Upload of " + Project)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat NM = NotificationManagerCompat.from(getApplicationContext());
        NM.notify(1, builder.build());
    }

    // Change Pages
    public void OpenHomePage(){
        Intent HomePage = new Intent(this, Home_Page.class);
        HomePage.putExtra("UserID", UsersID);
        HomePage.putExtra("UserName", UsersName);
        startActivity(HomePage);

    }

}
