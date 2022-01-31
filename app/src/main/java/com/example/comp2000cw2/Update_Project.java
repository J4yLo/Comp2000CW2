package com.example.comp2000cw2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.comp2000cw2.R;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Update_Project extends AppCompatActivity {

    private String UsersID;
    private String UsersName;
    private RequestQueue requestQueue;
    private String Project;
    private Uri IMGUri;

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
        setContentView(R.layout.activity_update_project);

        //Notifications Channel
        NotificationChannel Nchannel = new NotificationChannel("NotificationChannel", "Notifications", NotificationManager.IMPORTANCE_DEFAULT);
        Nchannel.setDescription("Notifications");
        NotificationManager notifyManager = getSystemService(NotificationManager.class);
        notifyManager.createNotificationChannel(Nchannel);

        //Variables
        ImageButton Back = findViewById(R.id.BackBtn);
        TextView Upload = findViewById(R.id.Upload);
        EditText projCode = findViewById(R.id.ProjectCode);
        EditText ProjectName = findViewById(R.id.ProjectName);
        EditText ProjectDesc = findViewById(R.id.ProjectDescription);
        TextView Addimg = findViewById(R.id.AddImgText);

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + projCode.getText();


                //Conversion of variables
                int UID;
                int ProjID;

                String[] Names;
                String FirstName;
                String Lastname;
                try {
                    UID = Integer.parseInt(UsersID);
                    ProjID = Integer.parseInt(projCode.getText().toString());
                    int indx = UsersName.lastIndexOf(' ');
                    FirstName = UsersName.substring(0, indx);
                    Lastname = UsersName.substring(indx + 1);

                }catch(Exception e){
                    UID = 0;
                    ProjID = 0;
                    FirstName = UsersName;
                    Lastname = "None";

                }

                //Get Year of Project
                int year = Calendar.getInstance().get(Calendar.YEAR);

                Project = projCode.getText().toString();
                //Toast.makeText(Update_Project.this, ProjID, Toast.LENGTH_SHORT).show();
                Map<String, Object> params = new ArrayMap<>();
                params.put("StudentID", UID);
                params.put("projectID", ProjID);
                params.put("Title", ProjectName.getText().toString());
                params.put("Description", ProjectDesc.getText().toString());
                params.put("Year",  year);
                params.put("First_Name", FirstName);
                params.put("Second_Name", Lastname);


                JSONObject programmeDetails = new JSONObject(params);
                JsonObjectRequest UpdateProgramme = new JsonObjectRequest (Request.Method.PUT, url, programmeDetails, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Update_Project.this, "Project Updated", Toast.LENGTH_SHORT).show();
                        projectnotify();
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Update_Project.this, "Failure to Update Programme", Toast.LENGTH_SHORT).show();
                    }
                });


          
                requestQueue = Volley.newRequestQueue(Update_Project.this);
                requestQueue.add(UpdateProgramme);
            }

        });


        //Back Button Function
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
            }
        });


        // Get Image
        ActivityResultLauncher<Intent> intents = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();


                            //Obtain Image URI
                            try {
                                IMGUri = data.getData();
                                Addimg.setText(IMGUri.toString());
                                Toast.makeText(Update_Project.this, "Image Obtained Successfully! Uploading Image...", Toast.LENGTH_SHORT).show();


                            } catch (Exception e) {

                                Toast.makeText(Update_Project.this, "Image Failed To Obtain", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                            //Upload Image
                            try{
                                if( projCode.getText().toString().isEmpty())
                                {
                                    Toast.makeText(Update_Project.this, "Please input project code before selecting an image", Toast.LENGTH_SHORT).show();
                                    Addimg.setText("Add Image");
                                }
                                else

                                {
                                    File Image = new File(URIInterpreter.getPath(IMGUri, getApplicationContext()));

                                    RequestBody Rb = RequestBody.create(MediaType.parse("image/*"), Image);

                                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", Image.getName(), Rb);
                                    Retrofit retrofit = NetworkClient.getRetrofit();
                                    UploadImg Upload = retrofit.create(UploadImg.class);

                                    Call call = Upload.uploadImage(part, Integer.parseInt(UsersID));


                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onResponse(Call call, retrofit2.Response response) {
                                            Toast.makeText(Update_Project.this, "Success Programme Uploaded", Toast.LENGTH_SHORT).show();
                                            IMGNotify();
                                            
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Toast.makeText(Update_Project.this, "Failure To Upload Image", Toast.LENGTH_SHORT).show();
                                            Addimg.setText("Add Image");
                                        }
                                    });
                                }
                            }catch (Exception e)
                            {
                                Toast.makeText(Update_Project.this, "Issue With Preparing Image For Upload", Toast.LENGTH_LONG).show();
                                //Toast.makeText(Update_Project.this, IMGUri.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });


        //Select Image
        Addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intents.launch(intent);
            }
        });
    }





    //notification
    public void projectnotify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "NotificationChannel")
                .setSmallIcon(R.drawable.ic_baseline_add_box_24)
                .setContentTitle("Update Project")
                .setContentText("Successful Update of project " + Project)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat NM = NotificationManagerCompat.from(getApplicationContext());
        NM.notify(1, builder.build());

    }

    public void IMGNotify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "NotificationChannel")
                .setSmallIcon(R.drawable.ic_baseline_add_box_24)
                .setContentTitle("Update Image")
                .setContentText("Successful Image upload of project " + Project)
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
