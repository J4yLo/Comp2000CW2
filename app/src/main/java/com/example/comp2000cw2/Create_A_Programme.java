package com.example.comp2000cw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Create_A_Programme extends AppCompatActivity {

    private String UsersID;
    private String UsersName;
    TextView Upload;
    RequestQueue requestQueue;

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




        //UI Variables
        Upload = findViewById(R.id.Upload);
        ImageButton Back = findViewById(R.id.BackBtn);

        EditText projTitle = findViewById(R.id.ProjectTitle);
        EditText projDesc = findViewById(R.id.ProjectDescription);
        TextView projImg = findViewById(R.id.AddImgText);



        //Get Year of Project
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String yearInString = String.valueOf(year);




        //Upload Button Function
        Upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";

                JsonObjectRequest  newProgramme = new JsonObjectRequest (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(Create_A_Programme.this, "Success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Create_A_Programme.this, "Failure to Upload Programme", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map getParams() throws AuthFailureError {
                        Map<String, Object> params = new HashMap<>();

                        params.put("title", projTitle.getText().toString());
                        params.put("first_Name", UsersName);
                        params.put("last_name", "World");
                        params.put("description", projDesc.getText().toString());


                        params.put("studentID", Integer.parseInt(UsersID));
                        params.put("year", year );


                        //Image Items
                        //params.put("thumbnailURL", "Null");
                        //params.put("posterURL", "Null");
                        //params.put("photo", "null");

                        Log.d("ProjArray At Start", "Response: " + params.toString());
                        return super.getParams();
                    }
                };


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









    // Change Pages
    public void OpenHomePage(){
        Intent HomePage = new Intent(this, Home_Page.class);
        HomePage.putExtra("UserID", UsersID);
        HomePage.putExtra("UserName", UsersName);
        startActivity(HomePage);

    }
}
